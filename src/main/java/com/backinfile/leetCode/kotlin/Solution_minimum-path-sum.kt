package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArrayArray
import org.junit.Test

class `Solution_minimum-path-sum` {
    fun minPathSum(grid: Array<IntArray>): Int {
        val dp = Array(grid.size) { IntArray(grid[0].size) }

        dp[0][0] = grid[0][0]

        for (i in 1 until grid.size) {
            dp[i][0] = grid[i][0] + dp[i - 1][0]
        }
        for (j in 1 until grid[0].size) {
            dp[0][j] = grid[0][j] + dp[0][j - 1]
        }

        for (i in 1 until grid.size) {
            for (j in 1 until grid[0].size) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1])
            }
        }

        return dp.last().last()
    }

    @Test
    fun test() {
        assert(7 == minPathSum("[[1,3,1],[1,5,1],[4,2,1]]".toIntArrayArray()))
        assert(12 == minPathSum("[[1,2,3],[4,5,6]]".toIntArrayArray()))
    }
}