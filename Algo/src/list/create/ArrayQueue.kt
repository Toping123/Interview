package list.create

/**
 * 基于数组实现的队列
 * @author LTP  2025/7/28
 */
class ArrayQueue(capacity: Int) : IQueue {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = ArrayQueue(5)
            queue.enqueue(1)
            queue.enqueue(2)
            queue.enqueue(3)
            queue.enqueue(4)
            queue.enqueue(5)
            println(queue.peek())
            println(queue.dequeue())
            println(queue.toArray().joinToString())
        }
    }

    /** 实现队列存储的数组 */
    private val array = IntArray(capacity)

    /** 队列头指针 */
    private var headIndex = 0

    /** 队列大小 */
    private var queueSize = 0

    override fun size(): Int {
        return queueSize
    }

    override fun isEmpty(): Boolean {
        return size() == 0
    }

    override fun enqueue(t: Int) {
        if (queueSize == array.size) {
            print("队列已满")
            return
        }
        // 计算队尾指针，指向队尾索引 + 1
        // 通过取余操作实现 rear 越过数组尾部后回到头部
        val rearIndex = (headIndex + queueSize) % array.size
        array[rearIndex] = t
        queueSize++
    }

    override fun dequeue(): Int? {
        val num = peek()
        headIndex = (headIndex + 1) % array.size
        queueSize--
        return num
    }

    override fun peek(): Int? {
        if (isEmpty()) {
            print("队列为空")
            return null
        }
        return array[headIndex]
    }

    override fun toArray(): IntArray {
        return array.copyOfRange(headIndex, headIndex + queueSize)
    }
}