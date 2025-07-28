package list.create

import base.ListNode

/**
 * 基于链表实现的队列
 * @author LTP  2025/7/28
 */
class LinkedListQueue : IQueue {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = LinkedListQueue()
            queue.enqueue(1)
            queue.enqueue(2)
            queue.enqueue(3)
            queue.enqueue(4)
            queue.enqueue(5)
            println(queue.front())
            println(queue.dequeue())
            println(queue.toArray().joinToString())
        }
    }

    /** 队列头指针 */
    private var front: ListNode? = null

    /** 队列尾指针 */
    private var rear: ListNode? = null

    /** 队列大小 */
    private var queueSize = 0

    override fun size(): Int {
        return queueSize
    }

    override fun isEmpty(): Boolean {
        return size() == 0
    }

    override fun enqueue(t: Int) {
        val newNode = ListNode(t)
        // 如果队列为空，则令头、尾节点都指向该节点
        if (isEmpty()) {
            front = newNode
            rear = newNode
        } else {
            // 如果队列不为空，则将该节点添加到尾节点后
            rear?.next = newNode
            rear = newNode
        }
        queueSize++
    }

    override fun dequeue(): Int? {
        val num = front()
        // 删除头节点
        front = front?.next
        queueSize--
        return num
    }

    override fun front(): Int? {
        return front?.value
    }

    override fun toArray(): IntArray {
        val array = IntArray(size())
        var temp = front
        for (i in array.indices) {
            array[i] = temp!!.value
            temp = temp.next
        }
        return array
    }
}
