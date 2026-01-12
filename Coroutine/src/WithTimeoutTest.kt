import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/**
 * @author LTP  2026/1/12
 */
object WithTimeoutTest {

    // 模拟一个不可取消的 Flow（比如某些硬件通信、第三方SDK回调等场景）
    val nonCancellableFlow: Flow<Int> = flow {
        for (i in 1..10) {
            println("[Flow] 第 $i 次迭代开始...")
            withContext(NonCancellable) {
                // 模拟不可中断的耗时操作
                delay(10000)
            }
            println("[Flow] 发射值: $i")
            emit(i)
        }
        println("[Flow] ✅ 全部完成")
    }

    @OptIn(ExperimentalTime::class)
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        // 这里超时会失效
       val time1 =measureTime {
            nonCancellableFlow.firstOrNullWithTimeout()
        }
        println("time1=$time1")
        // 这里超时会生效
        val time2 = measureTime {
            nonCancellableFlow.firstOrNullWithSeparateCoroutine()
        }
        println("time2=$time2")
    }

    /** 测试withTimeout的超时执行 */
    /**
     * 从Flow中获取第一个值，超时后返回null
     * @param timeout 超时时间，单位毫秒，默认1000毫秒
     * @param predicate 用于判断值是否符合条件的函数，默认null表示不判断
     * @return 第一个值或null
     *
     * 使用withTimeout
     */
    private suspend fun <T> Flow<T>.firstOrNullWithTimeout(
        timeout: Long = 1000L,
        predicate: (suspend (T) -> Boolean)? = null
    ): T? {
        val flowHashCode = this.hashCode()
        println("firstOrNullWithTimeout:flowHashCode=${flowHashCode},timeout=$timeout")

        // 等待结果，带超时
        val value = withTimeoutOrNull(timeout) {
            println("firstOrNullWithTimeout:flowHashCode=${flowHashCode},等待超时$timeout")
            if (predicate == null) firstOrNull() else firstOrNull(predicate)
        }.also {
            if (it == null) {
                println("firstOrNullWithTimeout:flowHashCode=${flowHashCode},超时未获取到数据")
            } else {
                println("firstOrNullWithTimeout:flowHashCode=${flowHashCode},未超时返回值=$it")
            }
        }
        return value
    }

    /** 测试单独协程的超时处理逻辑 */
    private suspend fun <T> Flow<T>.firstOrNullWithSeparateCoroutine(
        timeout: Long = 1000L,
        predicate: (suspend (T) -> Boolean)? = null
    ): T? {
        val flowHashCode = this.hashCode()
        println("firstOrNullWithSeparateCoroutine:flowHashCode=${flowHashCode},timeout=$timeout")

        val result = CompletableDeferred<T?>()

        // 创建独立的协程作用域，使其不会阻塞调用方
        val collectJob = CoroutineScope(currentCoroutineContext() + Job()).launch {
            try {
                val value = this@firstOrNullWithSeparateCoroutine.cancellable().run {
                    if (predicate == null) firstOrNull() else firstOrNull(predicate)
                }
                result.complete(value)
            } catch (e: CancellationException) {
                // 被取消时，如果result还未完成，则complete为null
                println(
                    "firstOrNullWithSeparateCoroutine:flowHashCode=${flowHashCode},协程取消${e.message}"
                )
                result.complete(null)
            } catch (e: Exception) {
                result.completeExceptionally(e)
                println(
                    "firstOrNullWithSeparateCoroutine:flowHashCode=${flowHashCode},协程异常${e.stackTraceToString()}"
                )
            }
        }

        // 等待结果，带超时
        val value = withTimeoutOrNull(timeout) {
            println("firstOrNullWithSeparateCoroutine:flowHashCode=${flowHashCode},等待超时$timeout")
            result.await()
        }

        // 超时后尝试取消收集协程
        if (!result.isCompleted) {
            collectJob.cancel()
            println("firstOrNullWithSeparateCoroutine:flowHashCode=${flowHashCode},超时未获取到数据")
        } else {
            println("firstOrNullWithSeparateCoroutine:flowHashCode=${flowHashCode},未超时返回值=$value")
        }
        return value
    }
}
