package com.backinfile.leetCode.kotlin

import org.junit.Test
import java.util.Collections
import java.util.LinkedList

class `Solution_minimum-window-substring` {


    fun minWindow(s: String, t: String): String {
        if (t.isEmpty() || s.isEmpty()) {
            return ""
        }

        val charMap = t.groupBy { it }.map { it.key to it.value.size }.toMap()
        val hashMap = hashMapOf<Char, LinkedList<Int>>()

        var minLength = -1
        var minLeft = -1
        var minRight = -1
        var last = 0


        fun checkAndDo(index: Int): Boolean {
            if (charMap.any { it.value > hashMap.computeIfAbsent(it.key) { LinkedList() }.count { i -> i >= last } }) {
                return false
            }
            if (minLength == -1 || index - last + 1 < minLength) {
                minLength = index - last + 1
                minLeft = last
                minRight = index
            }
            return true
        }

        fun moveLast() {
            while (last < s.length) {
                val ch = s[last]
                val count = charMap.getOrDefault(ch, 0)
                if (count == 0) {
                    last++
                    continue
                }
                val passed = hashMap.computeIfAbsent(ch) { LinkedList() }
                passed.removeAll { it < last }
                if (passed.size <= count) {
                    break
                }
                passed.pollFirst()
                last++
            }
        }


        while (last < s.length && !charMap.containsKey(s[last])) last++
        if (last >= s.length) {
            return ""
        }

//        hashMap[s[last]] = LinkedList()
//        hashMap[s[last]]!!.add(last)
        for (i in last until s.length) {
            val ch = s[i]
            val count = charMap.getOrDefault(ch, 0)
            if (count == 0) {
                continue
            }
            val passed = hashMap.computeIfAbsent(ch) { LinkedList() }
            passed.removeAll { it < last }
            passed.add(i)

            if (passed.size > count && s[i] == ch) {
                moveLast()
            }
            if (passed.size >= count) {
                if (checkAndDo(i)) {
                    last++
                }
            }
            moveLast()
        }
        if (minLength != -1) {
//            println(s.substring(minLeft..minRight))
            return s.substring(minLeft..minRight)
        }
//        println("")
        return ""
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