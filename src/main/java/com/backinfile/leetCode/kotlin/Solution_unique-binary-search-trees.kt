package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_unique-binary-search-trees` {
    fun numTrees(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = 1
        for (num in 2..n) {
            for (head in 0 until num) {
                dp[num] += dp[head] * dp[num - head - 1]
            }
        }
        return dp[n]
    }

    @Test
    fun test() {
        assert(numTrees(4) == 14)
        assert(numTrees(3) == 5)
        assert(numTrees(2) == 2)
        assert(numTrees(1) == 1)
    }
}