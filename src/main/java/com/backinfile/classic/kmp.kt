package com.backinfile.classic

import org.junit.Test

class kmp {
    fun search(str: String, pattern: String): Int {
        if (pattern.isEmpty()) {
            return -1
        }

        val next = run {
            val next = IntArray(pattern.length) { 0 }
            var head = 0
            for (i in 1 until pattern.length) {
                next[i] = head
                head = if (pattern[i] == pattern[head]) head + 1 else if (pattern[i] == pattern[0]) 1 else 0
            }
            next
        }

        println("next=${next.toList()}")

        var i = 0
        var p = 0
        while (i < str.length) {
            if (str[i] == pattern[p]) {
                p++
                i++
            } else if (p != next[p]) {
                println("rollback p from $p to ${next[p]}")
                p = next[p]
            } else {
                println("normal match i=$i")
                i++
                p = 0
            }
            if (p == pattern.length) {
                return i - pattern.length
            }
        }
        return -1
    }


    @Test
    fun test() {
        assert(testFunc("abaababcabaa", "ababcabaa"))
        assert(testFunc("abcasda", "e"))
        assert(testFunc("abcasda", "a"))
        assert(testFunc("abcasda", "abc"))
        assert(testFunc("abcasda", "asda"))
        assert(testFunc("aaaaaaac", "aac"))
        assert(testFunc("aaaaaaac", "aaaaaaac"))
        assert(testFunc("abcabeabcd", "abcd"))
    }

    private fun testFunc(str: String, pattern: String): Boolean {
        val output = search(str, pattern)
        val answer = str.indexOf(pattern)
        println("str=$str p:$pattern out=$output ans=$answer\n")
        return output == answer
    }
}