package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_target-sum` {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val dp = Array(nums.size) { IntArray(4005) { -1 } }

        fun dfs(index: Int, target: Int): Int {
            if (index >= nums.size) {
                return if (target == 0) 1 else 0
            }
            if (dp[index][target + 2002] == -1) {
                dp[index][target + 2002] = dfs(index + 1, target - nums[index]) +
                        dfs(index + 1, target + nums[index])
            }
            return dp[index][target + 2002]
        }
        return dfs(0, target)
    }


    @Test
    fun test() {
        5 assertEqualTo findTargetSumWays("[1,1,1,1,1]".toIntArray(), 3)
        1 assertEqualTo findTargetSumWays("[1]".toIntArray(), 1)
    }
}