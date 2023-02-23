package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_regular-expression-matching_2` {

    // 动态规划
    fun isMatch(s: String, p: String): Boolean {


        val matchMap = Array(s.length + 1) { Array(p.length + 1) { false } }

        // 初始化第一行
        matchMap[0][0] = true
        for (pIndex in p.indices step 2) {
            if (pIndex + 1 < p.length && p[pIndex + 1] == '*') {
                matchMap[0][pIndex + 2] = true
            } else {
                break
            }
        }

        for (index in 1..s.length) {
            for (pIndex in 1..p.length) {
                val curChar = s[index - 1]
                val pChar = p[pIndex - 1]
                matchMap[index][pIndex] = when (pChar) {
                    '.', curChar -> matchMap[index - 1][pIndex - 1]
                    '*' -> matchMap[index][pIndex - 2] || when (p[pIndex - 2]) {
                        '.', curChar -> matchMap[index - 1][pIndex]
                        else -> false
                    }

                    else -> false
                }
            }
        }
        return matchMap[s.length][p.length]
    }


    @Test
    fun test() {
        assert(!isMatch("aa", "a"))
        assert(isMatch("aa", "a*"))
        assert(isMatch("ab", ".*"))
        assert(isMatch("", ""))
        assert(isMatch("aaa", "a*a"))
    }

    @Test
    fun test2() {
        assert(isMatch("aaaaa", ".*a"))
        assert(isMatch("aaaab", ".*b"))
        assert(isMatch("aaaab", ".*ab"))
        assert(!isMatch("aaaab", ".*bc"))
    }

    @Test
    fun test3() {
        assert(!isMatch("aaaaaaaaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*"))
    }
}