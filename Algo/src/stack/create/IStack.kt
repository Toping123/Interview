package stack.create

/**
 * 栈的接口
 * @author LTP  2025/7/23
 */
interface IStack {
    /**
     * 获取栈的大小
     *
     * @return 栈的大小
     */
    fun size(): Int

    /**
     * 判断是否为空
     *
     * @return 是否为空
     */
    fun isEmpty(): Boolean

    /**
     * 入栈
     *
     * @param t 入栈的元素
     */
    fun push(t: Int)

    /**
     * 出栈
     *
     * @return 出栈的元素
     */
    fun pop(): Int?

    /**
     * 获取栈顶元素
     *
     * @return 栈顶元素
     */
    fun peek(): Int?

    /**
     * 遍历栈
     *
     * @return 栈的数组表示
     */
    fun toArray(): Array<Int>
}