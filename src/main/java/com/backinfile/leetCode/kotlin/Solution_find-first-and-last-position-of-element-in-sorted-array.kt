package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_find-first-and-last-position-of-element-in-sorted-array` {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) {
            return intArrayOf(-1, -1)
        }

        var left = 0
        var right = nums.lastIndex


        // 找符合值最右侧
        // right指向符合值的最右侧
        while (left < right) {
            val mid = (left + right + 1) / 2
            val midValue = nums[mid]
            if (midValue == target) {
                if (left == mid) {
                    break
                }
                left = mid
            } else if (midValue > target) {
                right = mid - 1
            } else {
                left = mid
            }
        }

        // 没有符合的值，直接退出
        if (nums[right] != target) {
            return intArrayOf(-1, -1)
        }
        val rightIndex = right

        // 找符合值最左侧
        left = 0
        right = rightIndex
        while (left < right) {
            val mid = (left + right) / 2
            val midValue = nums[mid]
            if (midValue == target) {
                if (right == mid) {
                    break
                }
                right = mid
            } else if (midValue <= target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return intArrayOf(left, rightIndex)
    }

    @Test
    fun test() {
        assert(testFunc("[5,7,7,8,8,10]", 8, "[3,4]"))
        assert(testFunc("[5,7,7,8,8,10]", 6, "[-1,-1]"))
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