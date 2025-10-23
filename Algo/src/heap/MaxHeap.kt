package heap

import java.util.PriorityQueue

/**
 * 大顶堆
 * @author LTP  2025/10/22
 */
class MaxHeap(numList: MutableList<Int>) {
    private val maxHeap = mutableListOf<Int>()

    init {
        // 将列表元素原封不动添加进堆
        maxHeap.addAll(numList)

    }

    /**
     * 获取堆的大小
     * @return 堆的大小
     */
    private fun size(): Int {
        return maxHeap.size
    }

    /**
     * 获取父节点的索引
     * @param i 父节点的索引
     */
    private fun parent(i: Int): Int {
        return (i - 1) / 2
    }

    /**
     * 获取左子节点的索引
     * @param i 左子节点的索引
     */
    private fun left(i: Int): Int {
        return 2 * i + 1
    }

    /**
     * 获取右子节点的索引
     * @param i 右子节点的索引
     */
    private fun right(i: Int): Int {
        return 2 * i + 2
    }

    /**
     * 交换元素
     * @param i 要交换的位置
     * @param j 交换到的位置
     */
    private fun swap(i: Int, j: Int) {
        val temp = maxHeap[i]
        maxHeap[i] = maxHeap[j]
        maxHeap[j] = temp
    }

    private fun siftDown(i: Int) {
        var position = i
        while (true) {
            // 判断节点 position, leftPosition, rightPosition中值最大的节点，记为maxPosition
            val leftPosition = left(position)
            val rightPosition = right(position)
            var maxPosition = position
            if (leftPosition < size() && maxHeap[leftPosition] > maxHeap[maxPosition]) {
                maxPosition = leftPosition
            }
            if (rightPosition < size() && maxHeap[rightPosition] > maxHeap[maxPosition]) {
                maxPosition = rightPosition
            }
            // 若节点 position 最大或索引leftPosition,rightPosition越界，则无须继续堆化，跳出
            if (maxPosition == position) break
            swap(position, maxPosition)
            // 循环向下堆化
            position = maxPosition
        }
    }
}