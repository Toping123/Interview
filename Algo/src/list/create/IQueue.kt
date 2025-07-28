package list.create

/**
 * 队列（单向）的接口
 * @author LTP  2025/7/23
 */
interface IQueue {
    /**
     * 获取队列的大小
     *
     * @return 队列的大小
     */
    fun size(): Int

    /**
     * 判断是否为空
     *
     * @return 是否为空
     */
    fun isEmpty(): Boolean

    /**
     * 入队
     *
     * @param t 入队的元素
     */
    fun enqueue(t: Int)

    /**
     * 出队
     *
     * @return 出队的元素
     */
    fun dequeue(): Int?

    /**
     * 获取队头元素
     *
     * @return 队头元素
     */
    fun peek(): Int?

    /**
     * 遍历栈
     *
     * @return 栈的数组表示
     */
    fun toArray(): IntArray
}