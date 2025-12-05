package array

import java.util.Arrays

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 链接：<a href="https://leetcode.cn/problems/two-sum">...</a>
 *
 * @author LTP  2025/11/7
 */
object TwoSum {

    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(3, 2, 4)
        println(twoSum1(array, 6).contentToString())
        println(twoSum2(array, 6).contentToString())
    }

    /**
     * 方法一：使用哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private fun twoSum1(numArray: IntArray, target: Int): IntArray {
        val numMap = mutableMapOf<Int, Int>()
        for (i in numArray.indices) {
            if (numMap.containsKey(target - numArray[i])) {
                return intArrayOf(numMap[target - numArray[i]]!!, i)
            }
            numMap[numArray[i]] = i
        }
        return intArrayOf()
    }

    /**
     * 方法二：使用双指针
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    private fun twoSum2(numArray: IntArray, target: Int): IntArray {
        for (i in numArray.indices) {
            for (j in i + 1 until numArray.size) {
                if (numArray[i] + numArray[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf()
    }
}