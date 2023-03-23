package com.backinfile.leetCode.kotlin.sword

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_zheng-ze-biao-da-shi-pi-pei-lcof` {
    fun isMatch(s: String, p: String): Boolean {
        val dp = Array(s.length + 1) { BooleanArray(p.length + 1) { false } }
        dp[0][0] = true
        for (j in 1 until p.length step 2) {
            if (p[j] == '*') dp[0][j + 1] = true
            else break
        }
        for (i in s.indices) {
            val ch = s[i]
            for (j in p.indices) {
                dp[i + 1][j + 1] = when {
                    p[j] == '*' -> (j >= 1 && dp[i + 1][j - 1]) || (dp[i][j + 1] && (p[j - 1] == '.' || p[j - 1] == ch))
                    p[j] == '.' -> dp[i][j]
                    else -> ch == p[j] && dp[i][j]
                }
            }
        }
        return dp[s.length][p.length]
    }

    @Test
    fun test() {
        true assertEqualTo isMatch("aaa", "ab*a*c*a")
        true assertEqualTo isMatch("mississippi", "mis*is*ip*.")
        true assertEqualTo isMatch("", "a*")
        true assertEqualTo isMatch("aa", "a*")
        false assertEqualTo isMatch("a", "aa")
        true assertEqualTo isMatch("ab", ".*")
        true assertEqualTo isMatch("aab", "c*a*b")
        false assertEqualTo isMatch("mississippi", "mis*is*p*.")
    }
}