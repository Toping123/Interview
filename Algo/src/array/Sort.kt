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
        println("冒泡排序结果：${intArray2.contentToString()}")

        val intArray3 = intArrayOf(12, 13, 4, 7, 2, 21, 8)
        insertionSort(intArray3)
        println("插入排序结果：${intArray3.contentToString()}")
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
            // 添加交换标志位，可提升冒泡排序的效率
            var swapFlag = false
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (j in 0 until i) {
                // 交换 nums[j] 与 nums[j + 1]
                if (nums[j] > nums[j + 1]) {
                    val temp = nums[j]
                    nums[j] = nums[j + 1]
                    nums[j + 1] = temp
                    swapFlag = true
                }
            }
            // 若未交换任何元素，则说明数组已排序，直接跳出循环
            if (!swapFlag) break
        }
    }

    /**
     * 插入排序：开启一个循环，每轮将未排序区间的第一个元素插入到已排序区间的正确位置
     * [https://www.hello-algo.com/chapter_sorting/insertion_sort/]
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param nums 待排序数组
     */
    private fun insertionSort(nums: IntArray) {
        // 外循环，未排序的index从[1,n)
        for (i in 1 until nums.size) {
            // 要操作的位置base
            val base = nums[i]
            var j = i - 1
            // 内循环，将base插入到已经排序[0,i)的正确位置
            while (j >= 0 && nums[j] > base) {
                // 将 nums[j] 向右移动一位
                nums[j + 1] = nums[j]
                j--
            }
            // 将 base 插入到正确位置
            nums[j + 1] = base
        }
    }
}