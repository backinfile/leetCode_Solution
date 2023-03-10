package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test
import kotlin.math.min

class `Solution_coin-change_2` {
    // 递归 + 记忆化搜索
    fun coinChange(coins: IntArray, amount: Int): Int {
        if (amount == 0) {
            return 0
        }

        val dp = IntArray(amount + 1)
        fun dfs(value: Int): Int {
            if (value == 0) return 0
            if (value < 0) return -1
            if (dp[value] != 0) return dp[value]
            var minCnt = Int.MAX_VALUE
            for (c in coins) {
                val result = dfs(value - c)
                if (result >= 0) {
                    minCnt = minOf(minCnt, result + 1)
                }
            }
            dp[value] = if (minCnt == Int.MAX_VALUE) -1 else minCnt
            return dp[value]
        }
        return dfs(amount)
    }

    @Test
    fun test() {
        20 assertEqualTo coinChange("[186,419,83,408]".toIntArray(), 6249)
        3 assertEqualTo coinChange("[1, 2, 5]".toIntArray(), 11)
        -1 assertEqualTo coinChange("[2]".toIntArray(), 3)
        0 assertEqualTo coinChange("[1]".toIntArray(), 0)
    }
}