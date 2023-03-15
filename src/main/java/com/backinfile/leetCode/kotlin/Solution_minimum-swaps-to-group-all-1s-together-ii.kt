package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_minimum-swaps-to-group-all-1s-together-ii` {
    fun minSwaps(nums: IntArray): Int {
        if (nums.size <= 3) {
            return 0
        }
        val count = nums.count { it == 1 }
        if (count <= 1) {
            return 0
        }

        if (nums.size <= count + 1) {
            return 0
        }

        var maxSum = 0
        var curSum = 0
        for (i in 0 until count - 1) {
            curSum += nums[i]
        }
        for (i in nums.indices) {
            curSum += nums[(i + count - 1) % nums.size]
            maxSum = maxOf(maxSum, curSum)
            curSum -= nums[i]
        }
        return count - maxSum
    }

    @Test
    fun test() {
        0 assertEqualTo minSwaps("[0,0,0,0,0,0]".toIntArray())
        1 assertEqualTo minSwaps("[0,1,0,1,1,0,0]".toIntArray())
        2 assertEqualTo minSwaps("[0,1,1,1,0,0,1,1,0]".toIntArray())
        0 assertEqualTo minSwaps("[1,1,0,0,1]".toIntArray())
    }
}