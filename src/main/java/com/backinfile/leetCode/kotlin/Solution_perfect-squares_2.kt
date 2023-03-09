package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_perfect-squares_2` {
    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1)

        for (i in 1..n) {
            dp[i] = i
            for (j in 1..i) {
                if (i < j * j) {
                    break
                }
                dp[i] = dp[i].coerceAtMost(dp[i - j * j] + 1)
            }
        }
        return dp[n]
    }

    private fun findSquare(n: Int): Int {
        var left = 1
        var right = n - 1
        while (left <= right) {
            val mid = (right - left) / 2 + left
            val p = mid * mid
            when {
                p > n -> right = mid - 1
                p < n -> left = mid + 1
                else -> return mid
            }
        }
        return left
    }

    @Test
    fun test() {
        3 assertEqualTo numSquares(12)
        2 assertEqualTo numSquares(13)
    }
}