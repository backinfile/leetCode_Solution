package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_find-first-and-last-position-of-element-in-sorted-array_3` {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) {
            return intArrayOf(-1, -1)
        }
        val right = biSearchUpper(nums, target)
        if (right !in nums.indices || nums[right] != target) {
            return intArrayOf(-1, -1)
        }
        val left = biSearchUpper(nums, target - 1) + 1
        if (left !in nums.indices || nums[left] != target) {
            return intArrayOf(-1, -1)
        }
        return intArrayOf(left, right)
    }

    // 寻找最后一个<=target的值
    private fun biSearchUpper(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = (left + right + 1) / 2   // 防溢出 (right - left + 1) / 2 + left
            val midValue = nums[mid]
            if (midValue <= target) {
                left = mid + 1 // 让left跨过right终止循环
            } else {
                right = mid - 1
            }
        }
        return right // 返回right表示要找小于等于target的值 mid取值应偏向于right
    }


    @Test
    fun test() {
        assert(testFunc("[5,7,7,8,8,10]", 6, "[-1,-1]"))
        assert(testFunc("[5,7,7,8,8,10]", 8, "[3,4]"))
        assert(testFunc("[]", 0, "[-1,-1]"))


        assert(testFunc("[5,7,7,8,8,8,8]", 8, "[3,6]"))
        assert(testFunc("[8,8,8,8]", 8, "[0,3]"))
        assert(testFunc("[8,8,8,8,9,10]", 8, "[0,3]"))
        assert(testFunc("[8]", 8, "[0,0]"))
    }

    private fun testFunc(inputStr: String, target: Int, answerStr: String): Boolean {
        val output = searchRange(Utils.toIntArray(inputStr), target).toList()
        val answer = Utils.toIntArray(answerStr).toList()
        println(output)
        return output == answer
    }
}