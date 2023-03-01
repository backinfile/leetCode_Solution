package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_edit-distance` {
    fun minDistance(word1: String, word2: String): Int {
        if (word1.isEmpty() || word2.isEmpty()) {
            return Math.max(word1.length, word2.length)
        }

        val dp = Array(word1.length + 1) { IntArray(word2.length + 1) }

        dp[0][0] = 0
        for (i in 1 .. word1.length) {
            dp[i][0] = i
        }
        for (i in 1 .. word2.length) {
            dp[0][i] = i
        }

        for (i in word1.indices) {
            for (j in word2.indices) {
                dp[i + 1][j + 1] = Math.min(
                    dp[i][j] + if (word1[i] == word2[j]) 0 else 1,
                    1 + Math.min(dp[i][j + 1], dp[i + 1][j])
                )
            }
        }
        return dp.last().last()
    }

    @Test
    fun test() {
//        assert(3 == minDistance("horse", "ros"))
//        assert(5 == minDistance("intention", "execution"))
        assert(27 == minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"))
    }
}