package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_unique-paths` {
    fun uniquePaths(_m: Int, _n: Int): Int {
        val m = Math.max(_m, _n)
        val n = Math.min(_m, _n)
        if (n <= 1) {
            return 1
        }

        val dp = Array(m) { IntArray(n) }
        dp[0][0] = 1
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 && j == 0) {
                    continue
                }
                dp[i][j] = (if (i - 1 >= 0) dp[i - 1][j] else 0) + if (j - 1 >= 0) dp[i][j - 1] else 0
            }
        }
//        println(dp[m - 1][n - 1])
        return dp[m - 1][n - 1]
    }


    fun uniquePaths_math(m: Int, n: Int): Int {
        var result = 1
        for (i in 1 until n) {
            result = result * (m + i - 1) / i
        }
        return result
    }

    @Test
    fun test() {
        assert(uniquePaths(9, 9) == 12870)
        assert(uniquePaths(3, 7) == 28)
        assert(uniquePaths(3, 2) == 3)
        assert(uniquePaths(7, 3) == 28)
        assert(uniquePaths(3, 3) == 6)
    }


    @Test
    fun test2() {
        assert(uniquePaths_math(9, 9) == 12870)
        assert(uniquePaths_math(3, 7) == 28)
        assert(uniquePaths_math(3, 2) == 3)
        assert(uniquePaths_math(7, 3) == 28)
        assert(uniquePaths_math(3, 3) == 6)
    }
}