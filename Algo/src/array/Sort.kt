package array

/**
 * 排序算法
 * @author LTP  2025/12/5
 */
object Sort {

    @JvmStatic
    fun main(args: Array<String>) {
        val intArray = intArrayOf(12, 13, 4, 7, 2, 21, 8)
        selectionSort(intArray)
        println("选择排序结果：${intArray.contentToString()}")
    }

    /**
     * 选择排序：开启一个循环，每轮从未排序区间选择最小的元素，将其放到已排序区间的末尾
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param nums 待排序数组
     */
    private fun selectionSort(nums: IntArray) {
        for (i in 0 until nums.size) {
            var minIndex = i
            // 找到未排序中最小数的位置
            for (j in i until nums.size) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j
                }
            }
            // 将找到的最小数置换到已排序的最后一个
            if (minIndex != i) {
                val temp = nums[minIndex]
                nums[minIndex] = nums[i]
                nums[i] = temp
            }
        }
    }
}