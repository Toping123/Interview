package hash.create.link

import hash.create.HashPair
import hash.create.IHash

/**
 * 基于链表实现的哈希表
 * @author LTP  2025/7/29
 */
class LinkedHash : IHash {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val hash = LinkedHash()
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
    private var buckets = MutableList(capacity) { mutableListOf<HashPair>() }

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
        val index = hash(key)
        for (pair in buckets[index]) {
            if (pair.key == key) {
                return pair.value
            }
        }
        return null
    }

    override fun put(key: Int, value: String) {
        if (loadFactor() > loadFactor) {
            extend()
        }
        val index = hash(key)
        for (pair in buckets[index]) {
            if (pair.key == key) {
                pair.value = value
                return
            }
        }
        buckets[index].add(HashPair(key, value))
        size++
    }

    override fun remove(key: Int) {
        val index = hash(key)
        val bucket = buckets[index]
        for (pair in bucket) {
            if (pair.key == key) {
                bucket.remove(pair)
                size--
                return
            }
        }
    }

    override fun extend() {
        // 暂存原哈希表
        val bucketsTmp = buckets
        capacity *= extendRadio
        // 重新创建哈希表
        buckets = MutableList(capacity) { mutableListOf() }
        size = 0
        // 重新哈希
        for (bucket in bucketsTmp) {
            for (pair in bucket) {
                put(pair.key, pair.value)
            }
        }
    }

    override fun print() {
        for (bucket in buckets) {
            for (pair in bucket) {
                println("${pair.key} -> ${pair.value}")
            }
        }
    }
}