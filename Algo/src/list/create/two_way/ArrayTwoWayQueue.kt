package list.create.two_way

/**
 * 基于数组实现的双向队列
 * @author LTP  2025/7/28
 */
class ArrayTwoWayQueue(capacity: Int) : ITwoWayQueue {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = ArrayTwoWayQueue(3)
            queue.enqueueLast(1)
            queue.enqueueLast(2)
            queue.enqueueLast(3)
            println(queue.peekFirst())
            println(queue.dequeueFirst())
            println(queue.toArray().joinToString())
        }
    }

    /** 模拟队列的数组 */
    private val array = IntArray(capacity)

    /** 队列头指针 */
    private var headIndex: Int = 0

    /** 队列大小 */
    private var queueSize = 0

    override fun size(): Int {
        return queueSize
    }

    override fun isEmpty(): Boolean {
        return size() == 0
    }

    override fun enqueueFirst(t: Int) {
        if (size() == array.size) throw RuntimeException("队列已满")

        // 队首指针向左移动一位
        // 通过取余操作实现 head 越过数组头部后回到尾部
        headIndex = (headIndex - 1 + array.size) % array.size
        array[headIndex] = t
        queueSize++
    }

    override fun enqueueLast(t: Int) {
        if (size() == array.size) throw RuntimeException("队列已满")
        // 队尾指针向右移动一位
        // 通过取余操作实现 tail 越过数组尾部后回到头部
        val tailIndex = (headIndex + queueSize - 1) % array.size
        array[tailIndex] = t
        queueSize++
    }

    override fun dequeueFirst(): Int? {
        return peekFirst().also {
            headIndex = (headIndex + 1) % array.size
            queueSize--
        }
    }

    override fun dequeueLast(): Int? {
        return peekLast().also {
            queueSize--
        }
    }

    override fun peekFirst(): Int? {
        if (isEmpty()) throw RuntimeException("队列为空")
        return array[headIndex]
    }

    override fun peekLast(): Int? {
        if (isEmpty()) throw RuntimeException("队列为空")
        return array[(headIndex + queueSize) % array.size]
    }

    override fun toArray(): IntArray {
        return array.copyOfRange(headIndex, headIndex + queueSize)
    }
}