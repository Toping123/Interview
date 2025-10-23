package heap

import java.util.PriorityQueue
import java.util.Queue

/**
 * top K问题：
 * 给定一个长度为的无序数组nums ，请返回数组中最大的K个元素
 * https://www.hello-algo.com/chapter_heap/top_k/#833
 * @author LTP  2025/10/22
 */
class TopK {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val instance = TopK()
            instance.topKHeap(intArrayOf(1, 6, 7, 4, 5, 3, 2), 3).also {
                println(it)
            }
        }
    }

    private fun topKHeap(nums: IntArray, k: Int): Queue<Int> {
        // 初始化一个小顶堆
        val heap = PriorityQueue<Int>()
        // 将数组的前 k 个元素入堆
        for (i in 0 until k) {
            heap.offer(nums[i])
        }

        // 从第 k+1 个元素开始，保持堆的长度为 k
        for (i in k until nums.size) {
            // 若当前元素大于堆顶元素，则将堆顶元素出堆、当前元素入堆
            if (nums[i] > heap.peek()) {
                heap.poll()
                heap.offer(nums[i])
            }
        }
        return heap
    }
}