package hash.create

/**
 * 哈希表的接口
 * @author LTP  2025/7/29
 */
interface IHash {
    /**
     * 获取哈希表的大小
     *
     * @return 哈希表的大小
     */
    fun size(): Int

    /**
     * 判断哈希表是否为空
     *
     * @return 是否为空
     */
    fun isEmpty(): Boolean

    /**
     * 获取哈希表的负载因子
     *
     * @return 负载因子
     */
    fun loadFactor(): Double

    /**
     * 哈希函数
     *
     * @param key 键
     * @return 哈希值
     */
    fun hash(key: Int): Int

    /**
     * 获取哈希表中的值
     *
     * @param key 键
     * @return 值
     */
    fun get(key: Int): String?

    /**
     * 向哈希表中添加键值对
     *
     * @param key 键
     * @param value 值
     */
    fun put(key: Int, value: String)

    /**
     * 从哈希表中删除键值对
     *
     * @param key 键
     */
    fun remove(key: Int)

    /**
     * 扩展哈希表的容量
     */
    fun extend()

    /**
     * 打印哈希表
     */
    fun print()
}