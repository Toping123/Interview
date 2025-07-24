package stack.create

/**
 * 基于数组实现的栈
 * @author LTP  2025/7/23
 */
class ArrayStack : IStack {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val stack = ArrayStack()
            stack.push(1)
            stack.push(2)
            stack.push(3)
            println(stack.peek())
            println(stack.pop())
            println(stack.toArray().joinToString())
        }
    }

    /** 使用动态数组来实现栈（mutableListOf本质上是一个动态数组） */
    private val arrayStack = mutableListOf<Int>()

    override fun size(): Int {
        return arrayStack.size
    }

    override fun isEmpty(): Boolean {
        return size() == 0
    }

    override fun push(t: Int) {
        arrayStack.add(t)
    }

    override fun pop(): Int? {
        return if (isEmpty()) null else arrayStack.removeLast()
    }

    override fun peek(): Int? {
        return if (isEmpty()) null else arrayStack.last()
    }

    override fun toArray(): Array<Int> {
        return arrayStack.toTypedArray()
    }
}