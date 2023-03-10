package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_burst-balloons_2` {

    // 反向考虑，dp
    fun maxCoins(_nums: IntArray): Int {
        val nums = intArrayOf(1) + _nums + intArrayOf(1)
        val dp = Array(nums.size) { IntArray(nums.size) }

        var result = 0
        for (len in 3..nums.size) {
            for (left in 0..nums.size - len) {
                val right = left + len - 1

                var score = 0
                for (mid in left + 1 until right) {
                    val cur = nums[left] * nums[mid] * nums[right]
                    score = maxOf(score, dp[left][mid] + cur + dp[mid][right])
                }
                if (len == nums.size) {
                    result = maxOf(result, score)
                } else {
                    dp[left][right] = score
                }
            }
        }
        return result
    }

    @Test
    fun test() {
        167 assertEqualTo maxCoins("[3,1,5,8]".toIntArray())
        10 assertEqualTo maxCoins("[1,5]".toIntArray())
    }
}