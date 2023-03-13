package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_partition-equal-subset-sum` {
    fun canPartition(nums: IntArray): Boolean {

        val sum = nums.sum()
        if (sum % 2 == 1) {
            return false
        }
        val target = sum / 2

        val dp = BooleanArray(target + 1) { false }
        dp[0] = true

        for (i in nums.indices) {
            for (j in dp.indices.reversed()) {
                if (j >= nums[i] && dp[j - nums[i]]) {
                    dp[j] = true
                }
            }
        }
        return dp.last()
    }

    @Test
    fun test() {
        false assertEqualTo canPartition("[1,2,5]".toIntArray())
        true assertEqualTo canPartition("[1,5,11,5]".toIntArray())
        false assertEqualTo canPartition("[1,2,3,5]".toIntArray())
    }
}