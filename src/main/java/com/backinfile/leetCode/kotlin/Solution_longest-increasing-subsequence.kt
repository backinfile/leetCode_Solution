package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_longest-increasing-subsequence` {

    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        var result = 0
        for (i in nums.indices) {
            val cur = nums[i]
            var len = 1
            for (j in 0 until i) {
                if (nums[j] < cur) {
                    len = len.coerceAtLeast(dp[j] + 1)
                }
            }
            dp[i] = len
            result = result.coerceAtLeast(len)
        }
        return result
    }

    @Test
    fun test() {
        4 assertEqualTo lengthOfLIS("[10,9,2,5,3,7,101,18]".toIntArray())
        4 assertEqualTo lengthOfLIS("[0,1,0,3,2,3]".toIntArray())
        1 assertEqualTo lengthOfLIS("[7,7,7,7,7,7,7]".toIntArray())
    }
}