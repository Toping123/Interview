package array

/**
 * 二分查找插入点
 * https://www.hello-algo.com/chapter_searching/binary_search_insertion/#1021
 * @author LTP  2025/11/7
 */
object BinarySearchInsertion {

    @JvmStatic
    fun main(args: Array<String>) {
        val numArray = intArrayOf(-1, 0, 3, 5, 9, 12)
        println("二分查找到的插入点(无重复)下标为：${binarySearchInsertion(numArray, 6)}")
        val numArray2 = intArrayOf(-1, 0, 3, 3, 3, 5, 5, 9, 12)
        println(
            "二分查找到的插入点(有重复)下标为：${
                binarySearchInsertionWithDuplicate(
                    numArray2,
                    3
                )
            }"
        )
        println("二分查找到的左边界下标为：${binarySearchLeftBoundary(numArray2, 6)}")
        println("二分查找到的右边界下标为：${binarySearchRightBoundary(numArray2, 3)}")

    }

    /**
     * 二分查找插入点（无重复元素）
     * @param numArray 有序数组
     * @param target 目标值
     * @return 目标值的插入点下标
     */
    private fun binarySearchInsertion(numArray: IntArray, target: Int): Int {
        // 左侧索引
        var left = 0
        // 右侧索引
        var right = numArray.size - 1
        // 循环条件，当左侧索引小于等于右侧索引时继续查找（注意要加等号）
        while (left <= right) {
            // 中间索引
            val mid = left + (right - left) / 2
            if (numArray[mid] == target) {
                return mid
            } else if (numArray[mid] > target) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }

    /**
     * 二分查找插入点（有重复元素）
     * @param numArray 有序数组
     * @param target 目标值
     * @return 目标值的插入点下标
     */
    private fun binarySearchInsertionWithDuplicate(numArray: IntArray, target: Int): Int {
        // 左侧元素
        var left = 0
        var right = numArray.size - 1
        while (left <= right) {
            val middle = left + (right - left) / 2
            if (numArray[middle] < target) {
                left = middle + 1
            } else {
                // 无论等于还是大于，右指针均-1
                right = middle - 1
            }
        }
        return left
    }

    /**
     * 二分查找左边界
     * @param numArray 有序数组
     * @param target 目标值
     * @return 目标值的左边界下标
     */
    private fun binarySearchLeftBoundary(numArray: IntArray, target: Int): Int {
        // 转换成查找目标值的插入点
        val targetPosition = binarySearchInsertionWithDuplicate(numArray, target)
        // 目标值不存在于数组中
        if (targetPosition == numArray.size || numArray[targetPosition] != target) {
            return -1
        }
        return targetPosition
    }

     /**
      * 二分查找右边界
      * @param numArray 有序数组
      * @param target 目标值
      * @return 目标值的右边界下标
      */
    private fun binarySearchRightBoundary(numArray: IntArray, target: Int): Int {
        // 转换成查找目标值+1的插入点，再-1即可
        val targetPosition = binarySearchInsertionWithDuplicate(numArray, target + 1) - 1
        // 目标值不存在于数组中
        if (targetPosition == -1 || numArray[targetPosition] != target) {
            return -1
        }
        return targetPosition
    }
}