package list.create.two_way

/**
 * 基于链表实现的双向队列
 * @author LTP  2025/7/28
 */
class LinkedListTwoWayQueue : ITwoWayQueue {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = LinkedListTwoWayQueue()
            queue.enqueueLast(1)
            queue.enqueueLast(2)
            queue.enqueueLast(3)
            println(queue.peekFirst())
            println(queue.dequeueFirst())
            println(queue.toArray().joinToString())
        }
    }

    /** 队列头节点 */
    private var head: TwoWayListNode? = null

    /** 队列尾节点 */
    private var tail: TwoWayListNode? = null

    /** 队列大小 */
    private var queueSize = 0

    override fun size(): Int {
        return queueSize
    }

    override fun isEmpty(): Boolean {
        return size() == 0
    }

    override fun enqueueFirst(t: Int) {
        val newNode = TwoWayListNode(t)
        if (isEmpty()) {
            head = newNode
            tail = newNode
        } else {
            head?.prev = newNode
            newNode.next = head
            head = newNode
        }
        queueSize++
    }

    override fun enqueueLast(t: Int) {
        val newNode = TwoWayListNode(t)
        if (isEmpty()) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            newNode.prev = tail
            tail = newNode
        }
        queueSize++
    }

    override fun dequeueFirst(): Int? {
        return peekFirst().also {
            head = head?.next
            head?.prev = null
            queueSize--
        }
    }

    override fun dequeueLast(): Int? {
        return  peekLast().also {
            tail = tail?.prev
            tail?.next = null
            queueSize--
        }
    }

    override fun peekFirst(): Int? {
        return head?.value
    }

    override fun peekLast(): Int? {
        return tail?.value
    }

    override fun toArray(): IntArray {
        val array = IntArray(size())
        var node = head
        for (i in 0 until size()) {
            array[i] = node?.value!!
            node = node.next
        }
        return array
    }
}