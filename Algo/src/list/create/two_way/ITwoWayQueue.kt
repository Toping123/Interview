package list.create.two_way

/**
 * 队列（双向）的接口
 * @author LTP  2025/7/23
 */
interface ITwoWayQueue {
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
     * 从队头入队
     *
     * @param t 入队的元素
     */
    fun enqueueFirst(t: Int)

    /**
     * 从队尾入队
     *
     * @param t 入队的元素
     */
    fun enqueueLast(t: Int)

    /**
     * 从队头出队
     *
     * @return 出队的元素
     */
    fun dequeueFirst(): Int?

    /**
     * 从队尾出队
     *
     * @return 出队的元素
     */
    fun dequeueLast(): Int?

    /**
     * 获取队头元素
     *
     * @return 队头元素
     */
    fun peekFirst(): Int?

    /**
     * 获取队尾元素
     *
     * @return 队尾元素
     */
    fun peekLast(): Int?

    /**
     * 遍历栈
     *
     * @return 栈的数组表示
     */
    fun toArray(): IntArray
}