package hash.create.open

import hash.create.HashPair
import hash.create.IHash

/**
 * 基于开放地址法实现的哈希表
 * @author LTP  2025/7/29
 */
class OpenHash : IHash {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val hash = OpenHash()
            hash.put(1, "1")
            hash.put(2, "2")
            hash.put(3, "3")
            hash.put(4, "4")
            hash.put(5, "5")
            hash.put(6, "6")
            hash.put(7, "7")
            hash.put(8, "8")
            hash.put(9, "9")
            hash.put(10, "10")
            hash.print()
        }
    }

    /** 哈希表的大小 */
    private var size = 0

    /** 哈希表的容量 */
    private var capacity = 4

    /** 哈希表的负载因子 */
    private var loadFactor = 0.75

    /** 哈希表的扩展倍数 */
    private var extendRadio = 2

    /** 哈希表的桶数组 */
    private var buckets = arrayOfNulls<HashPair?>(capacity)

    /** 哈希表的删除标记 */
    private val TOMBSTONE = HashPair(-1, "-1")

    override fun size(): Int {
        return size
    }

    override fun isEmpty(): Boolean {
        return size() == 0
    }

    override fun loadFactor(): Double {
        return (size() / capacity).toDouble()
    }

    override fun hash(key: Int): Int {
        return key % capacity
    }

    override fun get(key: Int): String? {
        val index = findBucket(key)
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            return buckets[index]?.value
        }
        return null
    }

    override fun put(key: Int, value: String) {
        if (loadFactor() > loadFactor) {
            extend()
        }
        val index = findBucket(key)
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            buckets[index]!!.value = value
            return
        }
        buckets[index] = HashPair(key, value)
        size++
    }

    override fun remove(key: Int) {
        val index = findBucket(key)
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            buckets[index] = TOMBSTONE
            size--
        }
    }

    override fun extend() {
        // 暂存原哈希表
        val bucketsTmp = buckets
        capacity *= extendRadio
        // 重新创建哈希表
        buckets = arrayOfNulls(capacity)
        size = 0
        // 重新哈希
        for (bucket in bucketsTmp) {
            if (bucket != null && bucket != TOMBSTONE) {
                put(bucket.key, bucket.value)
            }
        }
    }

    override fun print() {
        for (bucket in buckets) {
            when (bucket) {
                null -> {
                    println("null")
                }
                TOMBSTONE -> {
                    println("TOMBSTONE")
                }
                else -> {
                    println("${bucket.key} -> ${bucket.value}")
                }
            }
        }
    }

    /**
     * 查找哈希表中指定键的桶的位置
     * @param key 键
     * @return 桶的索引
     */
    private fun findBucket(key: Int): Int {
        var index = hash(key)
        var firstTombstone = -1
        // 线性探测，当遇到空桶时跳出
        while (buckets[index] != null) {
            // 若遇到 key ，返回对应的桶索引
            if (buckets[index]?.key == key) {
                // 若之前遇到了删除标记，则将键值对移动至该索引处
                if (firstTombstone != -1) {
                    buckets[firstTombstone] = buckets[index]
                    buckets[index] = TOMBSTONE
                    return firstTombstone // 返回移动后的桶索引
                }
                return index // 返回桶索引
            }
            // 记录遇到的首个删除标记
            if (firstTombstone == -1 && buckets[index] == TOMBSTONE) {
                firstTombstone = index
            }
            // 计算桶索引，越过尾部则返回头部
            index = (index + 1) % capacity
        }
        // 若 key 不存在，则返回添加点的索引
        return if (firstTombstone == -1) index else firstTombstone
    }
}