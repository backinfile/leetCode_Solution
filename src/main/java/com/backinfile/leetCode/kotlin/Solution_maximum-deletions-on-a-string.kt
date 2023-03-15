package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_maximum-deletions-on-a-string` {

    fun deleteString(s: String): Int {

        val dp = IntArray(s.length)

        fun dfs(index: Int): Int {
            if (index >= s.length) {
                return 0
            }
            if (dp[index] > 0) {
                return dp[index]
            }
            var left = index
            var max = 1
            for (i in index + 1 until s.length) {
                if (s[i] == s[left]) {
                    if (i - index + 1 == (left - index + 1) * 2) {
                        max = maxOf(max, dfs(left + 1) + 1)
                    }
                    left++
                } else {
                    left = index + if (s[i] == s[index]) 1 else 0
                }
            }
            dp[index] = max
            return max
        }
        return dfs(0)
    }

    @Test
    fun test() {
        2 assertEqualTo deleteString("abcabcdabc")
        4 assertEqualTo deleteString("aaabaab")
        5 assertEqualTo deleteString("aaaaa")
    }
}