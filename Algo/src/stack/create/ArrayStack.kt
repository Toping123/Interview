package stack.create

/**
 * @author LTP  2025/7/23
 */
class ArrayStack<T> : IStack<T> {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val stack = ArrayStack<Int>()
            stack.push(1)
            stack.push(2)
            stack.push(3)
            println(stack.peek())
            println(stack.pop())
            println(stack.toArray().joinToString())
        }
    }

    /** 使用动态数组来实现栈（mutableListOf本质上是一个动态数组） */
    private val arrayStack = mutableListOf<T>()

    override fun size(): Int {
        return arrayStack.size
    }

    override fun isEmpty(): Boolean {
        return size() == 0
    }

    override fun push(t: T) {
        arrayStack.add(t)
    }

    override fun pop(): T? {
        return if (isEmpty()) null else arrayStack.removeLast()
    }

    override fun peek(): T? {
        return if (isEmpty()) null else arrayStack.last()
    }

    override fun toArray(): Array<T> {
        val array = arrayOfNulls<Any>(size()) as Array<T>
        for (i in 0 until size()) {
            array[i] = arrayStack[i]
        }
        return array
    }
}