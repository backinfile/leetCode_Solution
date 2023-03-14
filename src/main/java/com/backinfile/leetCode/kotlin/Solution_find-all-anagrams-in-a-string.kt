package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.assertSortedEqualTo
import com.backinfile.toIntList
import org.junit.Test
import kotlin.math.abs

class `Solution_find-all-anagrams-in-a-string` {
    // 固定长度的滑动窗口
    fun findAnagrams(s: String, p: String): List<Int> {
        if (s.length < p.length) {
            return listOf()
        }

        val needMap = p.groupBy { it }.map { it.key to it.value.size }.toMap()
        val window = hashMapOf<Char, Int>()

        for (i in 0 until p.lastIndex) {
            window.compute(s[i]) { _, oldValue -> (oldValue ?: 0) + 1 }
        }

        val result = arrayListOf<Int>()
        for (i in p.lastIndex until s.length) {
            window.compute(s[i]) { _, oldValue -> (oldValue ?: 0) + 1 }

            if (needMap.all { window[it.key] == it.value }) {
                result += i - p.lastIndex
            }

            val firstChar = s[i - p.lastIndex]
            window.compute(firstChar) { _, oldValue -> (oldValue ?: 0) - 1 }
        }
        return result
    }

    @Test
    fun test() {
        "[0,6]".toIntList() assertSortedEqualTo findAnagrams("cbaebabacd", "abc")
        "[0,1,2]".toIntList() assertSortedEqualTo findAnagrams("abab", "ab")
    }
}