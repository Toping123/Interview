import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author LTP  2025/8/4
 */
class JoinTest {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            runBlocking {
                val joinTest = JoinTest()
                val job1 = joinTest.testJob1()
                delay(5000)
                println("job1 isActive:${job1}")
                job1?.join()
                val job2 = joinTest.testJob2()
                job2?.join()
            }
        }
    }

    private val scope = CoroutineScope(Dispatchers.Default)
    private var job1: Job? = null
    private var job2: Job? = null

    private fun testJob1(): Job? {
        job1 = scope.launch {
            println("1111111111")
            delay(1000)
            println("222222222222")
        }
        return job1
    }

    private fun testJob2(): Job? {
        job2 = scope.launch {
            println("3333333333")
            delay(1000)
            println("444444444444")
        }
        return job2
    }
}