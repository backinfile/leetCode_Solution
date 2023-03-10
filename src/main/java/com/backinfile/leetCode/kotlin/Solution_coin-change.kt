package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_coin-change` {
    fun coinChange(coins: IntArray, amount: Int): Int {
        if (amount == 0) {
            return 0
        }

        coins.sortDescending()
        val dp = IntArray(amount + 1) { -1 }
        dp[0] = 0

        for (value in 1..amount) {
            var minCnt = Int.MAX_VALUE
            for (c in coins) {
                for (cnt in minOf(minCnt, value / c) downTo 1) {
                    val beforeValue = value - c * cnt
                    if (beforeValue >= 0 && dp[beforeValue] >= 0) {
                        minCnt = minOf(minCnt, dp[beforeValue] + cnt)
                    }
                }
            }
            dp[value] = if (minCnt == Int.MAX_VALUE) -1 else minCnt
        }

        return dp[amount]
    }

    @Test
    fun test() {
        20 assertEqualTo coinChange("[186,419,83,408]".toIntArray(), 6249)
        3 assertEqualTo coinChange("[1, 2, 5]".toIntArray(), 11)
        -1 assertEqualTo coinChange("[2]".toIntArray(), 3)
        0 assertEqualTo coinChange("[1]".toIntArray(), 0)
    }
}