package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_burst-balloons` {


    // 反向考虑，dp+记忆化搜索
    fun maxCoins(_nums: IntArray): Int {
        val nums = intArrayOf(1) + _nums + intArrayOf(1)
        val dp = Array(nums.size) { IntArray(nums.size) }

        fun dfs(left: Int, right: Int): Int {
            if (dp[left][right] > 0) {
                return dp[left][right]
            }

            var score = 0
            for (i in left + 1 until right) {
                val cur = nums[left] * nums[i] * nums[right]
                score = maxOf(score, dfs(left, i) + cur + dfs(i, right))
            }
            dp[left][right] = score
            return score
        }

        return dfs(0, nums.lastIndex)
    }

    @Test
    fun test() {
        167 assertEqualTo maxCoins("[3,1,5,8]".toIntArray())
        10 assertEqualTo maxCoins("[1,5]".toIntArray())
    }
}