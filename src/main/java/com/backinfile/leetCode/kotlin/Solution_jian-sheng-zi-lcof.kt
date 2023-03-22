package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test
import kotlin.math.max

class `Solution_jian-sheng-zi-lcof` {
    fun cuttingRope(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[1] = 1
        dp[2] = 1
        for (i in 3..n) {
            var cur = 0
            for (k in 1 until Math.min(i, n / 2 + 1)) {
                cur = maxOf(cur, k * dp[i - k], k * (i - k))
            }
            dp[i] = cur
        }
        return dp[n]
    }

    @Test
    fun test() {
        324 assertEqualTo cuttingRope(16)
        9 assertEqualTo cuttingRope(6)
        2 assertEqualTo cuttingRope(3)
        4 assertEqualTo cuttingRope(4)
        36 assertEqualTo cuttingRope(10)
        54 assertEqualTo cuttingRope(11)
        324 assertEqualTo cuttingRope(16)
        486 assertEqualTo cuttingRope(17)
    }
}