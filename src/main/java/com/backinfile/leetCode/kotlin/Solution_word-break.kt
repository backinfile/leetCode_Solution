package com.backinfile.leetCode.kotlin

import com.backinfile.toStrList
import org.junit.Test

class `Solution_word-break` {

    // 基于Tire树的广度搜索
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        // 构建tire
        val tire = Tire()
        for (str in wordDict) {
            tire.add(str)
        }
        // 先略作剪枝，去除字典中的重复字符串
        for (str in wordDict) {
            tire.removeRepeat(str)
        }

        // 记录正在移动中的trie树节点
        var curSet = hashSetOf<Tire>()
        curSet.add(tire)

        var nextSet = hashSetOf<Tire>()
        for (i in s.indices) {
            val childIndex = s[i] - 'a'
            for (cur in curSet) {
                val childNode = cur.children[childIndex] ?: continue
                nextSet.add(childNode) // curSet中所有的节点沿着s走一步

                if (childNode.str != null) { // 走到一个str终点时，开启匹配下一个单词
                    nextSet.add(tire)
                }
            }
            curSet.clear()
            curSet = nextSet.also { nextSet = curSet } // 交换curSet与nextSet
        }
        return curSet.any { it.str != null } // 只要正好有走到str结尾的即可
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

        fun removeRepeat(str: String) {
            var cur: Tire? = this
            for (ch in str) {
                if (cur == null) {
                    return
                }
                cur = cur.children[ch - 'a']
            }
            while (true) {
                for (ch in str) {
                    if (cur == null) {
                        return
                    }
                    cur = cur.children[ch - 'a']
                }
                if (cur?.str != null) {
                    cur.str = null
                }
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