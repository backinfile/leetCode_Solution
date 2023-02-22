package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_longest-palindromic-substring` {
    fun longestPalindrome(s: String): String {
        if (s == "") {
            return "";
        }


        var maxLength = 1;
        var maxLeft = 0;
        var maxRight = 0;

        val map = Array(s.length) { Array(s.length) { false } }

        for (length in 1..s.length) {
            for (right in s.indices) {
                val left = right - length + 1
                if (left < 0) {
                    continue
                }

                when (length) {
                    1 -> map[left][right] = true
                    2 -> if (s[left] == s[right]) {
                        map[left][right] = true

                        if (length > maxLength) {
                            maxLength = length
                            maxLeft = left
                            maxRight = right
                        }
                    }
                    else -> if (map[left + 1][right - 1] && s[left] == s[right]) {
                        map[left][right] = true;

                        if (length > maxLength) {
                            maxLength = length
                            maxLeft = left
                            maxRight = right
                        }
                    }
                }
            }
        }

//        println(s.substring(maxLeft, maxRight + 1))
        return s.substring(maxLeft, maxRight + 1)
    }


    @Test
    fun test() {
        assert(longestPalindrome("babad").length == 3)
        assert(longestPalindrome("cbbd") == "bb")
        assert(longestPalindrome("cbed").length == 1)
        assert(longestPalindrome("bb").length == 2)
    }
}