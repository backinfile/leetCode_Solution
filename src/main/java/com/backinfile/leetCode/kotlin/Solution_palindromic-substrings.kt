package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_palindromic-substrings` {
    fun countSubstrings(s: String): Int {
        val dp = Array(s.length + 1) { BooleanArray(s.length + 1) { false } }

        var result = 0
        for (len in 1..s.length) {
            for (left in 0..s.length - len) {
                val right = left + len - 1
                val match = when (len) {
                    1 -> true
                    2 -> s[left] == s[right]
                    else -> s[left] == s[right] && dp[left + 1][right - 1]
                }
                if (match) {
                    dp[left][right] = true
                    result += 1
                }
            }
        }
        return result
    }

    @Test
    fun test() {
        3 assertEqualTo countSubstrings("abc")
        6 assertEqualTo countSubstrings("aaa")
    }
}