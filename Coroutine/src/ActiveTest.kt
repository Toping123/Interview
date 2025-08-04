import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ActiveTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runBlocking {
                val activeTest = ActiveTest()
                repeat(1000) {
                    activeTest.startActive()
                }
                withContext(Dispatchers.Default) {
                    repeat(1000) {
                        activeTest.startActive()
                    }
                }
                activeTest.cancelActive()

                delay(5_000)
            }
        }
    }

    private var activeJob: Job? = null
    private var needExecute = true

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private fun startActive() {
        if (needExecute || activeJob?.isActive == true) {
            needExecute = false
            activeJob?.cancel()
            activeJob = scope.launch {
                println("1111111111")
                delay(1000)
                println("222222222222")
            }
        }
    }

    private fun cancelActive() {
        println("cancelActive")
        activeJob?.cancel()
    }
}