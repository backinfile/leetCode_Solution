package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_shortest-unsorted-continuous-subarray` {


    // 找到左侧第一个下降的leftIndex，右侧第一个上升的rightIndex
    // 找到leftIndex..rightIndex中的最小值与最大值，分别找到排序后对应的位置，两个位置之间即是结果
    fun findUnsortedSubarray(nums: IntArray): Int {
        if (nums.size <= 1) {
            return 0
        }
        val leftOrder = run {
            var r = 1
            while (r < nums.size && nums[r - 1] <= nums[r]) r++
            r - 1
        }
        if (leftOrder == nums.lastIndex) {
            return 0
        }
        val rightOrder = run {
            var l = nums.lastIndex - 1
            while (l >= 0 && nums[l] <= nums[l + 1]) l--
            l + 1
        }

        val slice = nums.slice(leftOrder..rightOrder)
        val minValue = slice.minOf { it }
        val maxValue = slice.maxOf { it }

        val leftEdge = biSearch(nums, 0, leftOrder, minValue + 1)
        val rightEdge = biSearch(nums, rightOrder, nums.lastIndex, maxValue) - 1
        return rightEdge - leftEdge + 1
    }

    // 找第一个>=target的元素
    private fun biSearch(nums: IntArray, from: Int, to: Int, target: Int): Int {
        var left = from
        var right = to
        while (left <= right) {
            val mid = (left + right) ushr 1
            if (nums[mid] >= target) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }

    @Test
    fun test() {
        3 assertEqualTo findUnsortedSubarray("[2,3,3,2,4]".toIntArray())
        5 assertEqualTo findUnsortedSubarray("[2,6,4,8,10,9,15]".toIntArray())
        0 assertEqualTo findUnsortedSubarray("[1,2,3,4]".toIntArray())
        0 assertEqualTo findUnsortedSubarray("[1]".toIntArray())
    }
}