package com.backinfile.leetCode.kotlin

import com.backinfile.toCharArrayArray
import org.junit.Test

class `Solution_maximal-square_2` {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val dp = Array(matrix.size + 1) { IntArray(matrix[0].size + 1) }

        var result = 0

        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (matrix[i][j] == '0') {
                    continue
                }
                val lastRect = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j])
                dp[i + 1][j + 1] = lastRect + 1
                result = result.coerceAtLeast(lastRect + 1)
            }
        }
        return result * result
    }


    @Test
    fun test() {
        assert(4 == maximalSquare("[[\"1\",\"0\",\"1\",\"0\"],[\"1\",\"0\",\"1\",\"1\"],[\"1\",\"0\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\"]]".toCharArrayArray()))

        assert(1 == maximalSquare("[[\"0\",\"1\"]]".toCharArrayArray()))
        assert(1 == maximalSquare("[[\"0\"],[\"1\"]]".toCharArrayArray()))

        assert(4 == maximalSquare("[[\"1\",\"0\",\"1\",\"0\",\"0\"],[\"1\",\"0\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"0\",\"0\",\"1\",\"0\"]]".toCharArrayArray()))
        assert(1 == maximalSquare("[[\"0\",\"1\"],[\"1\",\"0\"]]".toCharArrayArray()))
        assert(0 == maximalSquare("[[\"0\"]]".toCharArrayArray()))
    }
}