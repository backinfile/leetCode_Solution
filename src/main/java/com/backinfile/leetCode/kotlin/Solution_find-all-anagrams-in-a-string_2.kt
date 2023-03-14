package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.assertSortedEqualTo
import com.backinfile.toIntList
import org.junit.Test
import kotlin.math.abs

class `Solution_find-all-anagrams-in-a-string_2` {
    // 不固定长度的滑动窗口
    fun findAnagrams(s: String, p: String): List<Int> {
        if (s.length < p.length) {
            return listOf()
        }

        val result = arrayListOf<Int>()
        val needs = IntArray(26)
        p.forEach { needs[it - 'a']++ }

        var left = 0
        var right = 0
        while (right < s.length) {
            if (needs[s[right] - 'a'] > 0) {
                needs[s[right] - 'a']--
                if (right - left + 1 == p.length) {
                    result += left
                }
                right++
            } else {
                needs[s[left] - 'a']++
                left++
            }
        }
        return result
    }

    @Test
    fun test() {
        "[0,6]".toIntList() assertSortedEqualTo findAnagrams("cbaebabacd", "abc")
        "[0,1,2]".toIntList() assertSortedEqualTo findAnagrams("abab", "ab")
    }
}