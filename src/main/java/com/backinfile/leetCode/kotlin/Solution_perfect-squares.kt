package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_perfect-squares` {
    fun numSquares(n: Int): Int {
        var minCount = Int.MAX_VALUE

        fun dp(curCnt: Int, curValue: Int) {
            if (curValue == 0) {
                minCount = minCount.coerceAtMost(curCnt)
            }
            if (curCnt >= minCount) {
                return
            }
            for (a in findSquare(curValue) downTo 1) {
                val p = a * a
                if (curValue >= p) {
                    dp(curCnt + curValue / p, curValue % p)
                }
            }
        }
        dp(0, n)
        return minCount
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