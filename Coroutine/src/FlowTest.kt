import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * first() 判断：**有没有元素**，不管元素本身是不是 null
 * firstOrNull() 判断：**有没有元素**；没有元素返回 null；有元素返回第一个元素（元素可以是 null）
 * Flow<T?> 的坑：**元素可空 ≠ 流为空**，这是很多 Android 开发踩坑点。
 *
 * @author Toping  2026/9/10
 */
class FlowTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runBlocking {
                val flow1 = flow<String?> { emit(null) }
                println(flow1.firstOrNull())
                println(flow1.first())

                val flow2 = emptyFlow<String>()
                println(flow2.firstOrNull())
//                println(flow2.first()) // 抛出异常NoSuchElementException
            }
        }
    }
}