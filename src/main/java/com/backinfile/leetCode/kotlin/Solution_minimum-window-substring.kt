package com.backinfile.leetCode.kotlin

import org.junit.Test
import java.util.Collections
import java.util.LinkedList

class `Solution_minimum-window-substring` {


    fun minWindow(s: String, t: String): String {
        if (t.isEmpty() || s.isEmpty()) {
            return ""
        }
        val charMap = t.groupBy { it }.map { it.key to it.value.size }.toMap() // 需求的charMap
        var totalCnt = charMap.values.sum() // 当前需求的char的数目

        val hashMap = hashMapOf<Char, Int>() // 存储当前last~i之间提供的char

        var (minLength, minLeft, minRight) = arrayOf(-1, -1, -1) // 最终结果

        var last = 0 // 第一个指针

        // 移动last指针
        fun moveLast(_force: Boolean) {
            var force = _force
            while (last < s.length) {
                val ch = s[last]
                val count = charMap.getOrDefault(ch, 0)
                if (count == 0) {
                    last++
                    assert(!force)
                    continue
                }
                val passed = hashMap.getOrDefault(ch, 0)
                if (passed > count || force) {
                    assert(passed > 0)
                    hashMap[ch] = passed - 1
                    last++
                    if (passed == count) {
                        totalCnt++
                    }
                    force = false
                } else {
                    break
                }
            }
        }

        // 先过滤掉开头不需求的char
        while (last < s.length && !charMap.containsKey(s[last])) last++
        if (last >= s.length) {
            return ""
        }

        for (i in last until s.length) {
            val ch = s[i]
            val count = charMap.getOrDefault(ch, 0)
            if (count == 0) {
                continue
            }
            var passed = hashMap.getOrDefault(ch, 0)
            if (passed < count) totalCnt--
            passed += 1
            hashMap[ch] = passed

            if (passed > count && s[i] == ch) {
                moveLast(false)
            }
            if (passed >= count) {
                if (totalCnt == 0) {
                    if (minLength == -1 || i - last + 1 < minLength) {
                        minLength = i - last + 1
                        minLeft = last
                        minRight = i
                    }
                    moveLast(true)
                }
            }
            moveLast(false)
        }
        return (if (minLength != -1) s.substring(minLeft..minRight) else "").also {
            println(it)
        }
    }

    @Test
    fun test() {
        assert("b" == minWindow("ab", "b"))
        assert("BANC" == minWindow("ADOBECODEBANC", "ABC"))
        assert("EC" == minWindow("ADOBECODEBANC", "CE"))
        assert("ANC" == minWindow("ADOBECODEBANC", "AC"))
        assert("ADOBECODEBA" == minWindow("ADOBECODEBANC", "AA"))
        assert("ADOBECODEBA" == minWindow("ADOBECODEBANC", "AABC"))
        assert("a" == minWindow("a", "a"))
        assert("" == minWindow("a", "aa"))
    }
}