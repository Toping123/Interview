package stack.create

import base.ListNode

/**
 * 基于链表实现的栈
 * @author LTP  2025/7/24
 */
class LinkedListStack : IStack {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val stack = LinkedListStack()
            stack.push(1)
            stack.push(2)
            stack.push(3)
            println(stack.peek())
            println(stack.pop())
            println(stack.toArray().joinToString())
        }
    }

    /** 栈顶指针 */
    private var stackPeek: ListNode? = null

    /** 栈大小 */
    private var stackSize = 0

    override fun size(): Int {
        return stackSize
    }

    override fun isEmpty(): Boolean {
        return size() == 0
    }

    override fun push(t: Int) {
        val node = ListNode(t)
        node.next = stackPeek
        stackPeek = node
        stackSize++
    }

    override fun pop(): Int? {
        val num = peek()
        stackPeek = stackPeek?.next
        stackSize--
        return num
    }

    override fun peek(): Int? {
        return stackPeek?.value
    }

    override fun toArray(): Array<Int> {
        if (isEmpty()) return emptyArray()
        var node = stackPeek
        val list = mutableListOf<Int>()
        while (node != null) {
            list.add(node.value)
            node = node.next
        }
        return list.toTypedArray()
    }
}