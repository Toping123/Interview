package array

/**
 * 二分查找
 * <a href="https://leetcode-cn.com/problems/binary-search/"/>
 * @author LTP  2025/10/23
 */
object BinarySearch {

    @JvmStatic
    fun main(args: Array<String>) {
        val numArray = intArrayOf(-1, 0, 3, 5, 9, 12)
        println("二分查找到的下标为：${binarySearch(numArray, 5)}")
    }

    /**
     * 二分查找
     * @param numArray 有序数组
     * @param target 目标值
     * @return 目标值在数组中的下标，若不存在则返回-1
     */
    private fun binarySearch(numArray: IntArray, target: Int): Int {
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
        return -1
    }
}