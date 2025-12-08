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

        val intArray2 = intArrayOf(12, 13, 4, 7, 2, 21, 8)
        bubbleSort(intArray2)
        println("选择排序结果：${intArray2.contentToString()}")
    }

    /**
     * 选择排序：开启一个循环，每轮从未排序区间选择最小的元素，将其放到已排序区间的末尾
     * [https://www.hello-algo.com/chapter_sorting/selection_sort/]
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

    /**
     * 冒泡排序：开启一个循环，每轮从前往后比较相邻元素，将较大的元素往后交换，每轮结束将最大元素放到未排序区间的最后
     * [https://www.hello-algo.com/chapter_sorting/bubble_sort/]
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param nums 待排序数组
     */
    private fun bubbleSort(nums: IntArray) {
        // 外循环：未排序区间为 [0, i]
        for (i in nums.size - 1 downTo 1) {
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (j in 0 until i) {
                // 交换 nums[j] 与 nums[j + 1]
                if (nums[j] > nums[j + 1]) {
                    val temp = nums[j]
                    nums[j] = nums[j + 1]
                    nums[j + 1] = temp
                }
            }
        }
    }
}