package com.backinfile.leetCode.kotlin

import com.backinfile.toStrList
import org.junit.Test
import java.util.*

class `Solution_word-break_3` {

    // 动态规划+trie
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val tire = Tire()
        for (str in wordDict) {
            tire.add(str)
        }

        val dp = BooleanArray(s.length + 1)
        dp[0] = true
        for (i in s.indices) {
            if (!dp[i + 1 - 1]) {
                continue
            }
            for (matchIndex in tire.match(s, i)) {
                dp[matchIndex + 1] = true
            }
        }
        println(dp.contentToString())
        return dp[s.length]
    }

    class Tire {
        val children = Array<Tire?>(26) { null }
        var str: String? = null

        fun add(str: String) {
            var cur = this
            for (ch in str) {
                val childIndex = ch - 'a'
                if (cur.children[childIndex] == null) {
                    val tire = Tire()
                    cur.children[childIndex] = tire
                    cur = tire
                } else {
                    cur = cur.children[childIndex]!!
                }
            }
            cur.str = str
        }

        fun match(s: String, from: Int): Sequence<Int> = sequence {
            var cur: Tire? = this@Tire
            for (i in from until s.length) {
                if (cur == null) {
                    return@sequence
                }
                cur = cur.children[s[i] - 'a']
                cur?.str?.let { yield(i) }
            }
        }
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