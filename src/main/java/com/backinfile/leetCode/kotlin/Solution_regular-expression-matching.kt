package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_regular-expression-matching` {

    // 广度优先搜索
    fun isMatch(s: String, p: String): Boolean {
        val length = s.length
        val patternLength = p.length

        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(0, 0))

        while (!queue.isEmpty()) {
            val (index, pIndex) = queue.removeFirst()
            if (index >= length && pIndex >= patternLength) {
                return true
            }
            if (pIndex >= patternLength) { // pattern 耗尽
                continue
            }
            if (index >= length) { // 匹配完成 消耗掉*
                var lastIndex = pIndex;
                while (lastIndex + 1 < patternLength && p[lastIndex + 1] == '*') {
                    lastIndex += 2;
                }
                if (lastIndex >= patternLength) {
                    return true;
                }
                continue
            }
            if (pIndex + 1 < patternLength && p[pIndex + 1] == '*') {
                val pChar = p[pIndex]
                var pIndexOffset = 2;
                while (pIndex + 1 + pIndexOffset < patternLength && pChar == p[pIndex + pIndexOffset] && p[pIndex + pIndexOffset + 1] == '*') {
                    pIndexOffset += 2
                }

                queue.add(Pair(index, pIndex + pIndexOffset));

                for (nextIndex in index until length) {
                    if (pChar == '.' || pChar == s[nextIndex]) {
                        queue.add(Pair(nextIndex + 1, pIndex + pIndexOffset));
                    } else {
                        break
                    }
                }
                continue
            }

            val pChar = p[pIndex]
            if (pChar == '.' || pChar == s[index]) {
                queue.add(Pair(index + 1, pIndex + 1));
            }
        }
        return false
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