package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_target-sum_2` {
    // 假设a是最终结果中的正数部分，b是负数部分的绝对值
    // sum = a + b
    // target = a - b
    // 则 2a = sum + target
    // a = (sum + target) / 2
    // 问题转化为从nums中挑选任意个数之和为a
    fun findTargetSumWays(nums: IntArray, _target: Int): Int {
        val target = Math.abs(_target)
        val sum = nums.sum()
        if ((sum + target) % 2 == 1) {
            return 0
        }
        val t = (sum + target) / 2

        val dp = IntArray(t + 1)
        dp[0] = 1
        for (i in nums.indices) {
            for (j in dp.indices.reversed()) {
                if (j - nums[i] >= 0) {
                    dp[j] += dp[j - nums[i]]
                }
            }
        }
        return dp[t]
    }


    @Test
    fun test() {
        5 assertEqualTo findTargetSumWays("[1,1,1,1,1]".toIntArray(), 3)
        1 assertEqualTo findTargetSumWays("[1]".toIntArray(), 1)
    }
}