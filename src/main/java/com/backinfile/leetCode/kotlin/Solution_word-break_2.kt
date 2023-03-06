package com.backinfile.leetCode.kotlin

import com.backinfile.toStrList
import org.junit.Test

class `Solution_word-break_2` {

    // 动态规划，转化为背包问题
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dict = hashSetOf<String>()
        dict.addAll(wordDict)

        val dp = BooleanArray(s.length + 1)
        dp[0] = true
        for (i in s.indices) {
            for (j in 0..i) {
                if (dp[j] && dict.contains(s.substring(j, i + 1))) {
                    dp[i + 1] = true
                    break
                }
            }
        }
        return dp[s.length]
    }


    @Test
    fun test() {
        assert(wordBreak("leetcode", "[\"leet\", \"code\"]".toStrList()))
        assert(
            !wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "[\"aa\",\"aaa\",\"aaaa\",\"aaaaa\",\"aaaaaa\",\"aaaaaaa\",\"aaaaaaaa\",\"aaaaaaaaa\",\"aaaaaaaaaa\",\"ba\"]".toStrList()
            )
        )
        assert(
            !wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                "[\"a\",\"aa\",\"aaa\",\"aaaa\",\"aaaaa\",\"aaaaaa\",\"aaaaaaa\",\"aaaaaaaa\",\"aaaaaaaaa\",\"aaaaaaaaaa\"]".toStrList()
            )
        )
        assert(wordBreak("applepenapple", "[\"apple\", \"pen\"]".toStrList()))
        assert(!wordBreak("catsandog", "[\"cats\", \"dog\", \"sand\", \"and\", \"cat\"]".toStrList()))
    }
}