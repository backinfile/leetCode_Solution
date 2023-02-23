package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_roman-to-integer` {
    fun romanToInt(s: String): Int {
        if (s.isEmpty()) {
            return 0
        }

        var result = 0
        var index = 0
        while (index < s.length) {
            val nextChar = if (index + 1 < s.length) s[index + 1] else null
            result += when (s[index]) {
                'M' ->  1000
                'D' ->  500
                'C' ->  when (nextChar) {
                    'D' -> {index++; 400}
                    'M' -> {index++; 900}
                    else -> 100
                }
                'L' ->  50
                'X' ->  when (nextChar) {
                    'L' -> {index++; 40}
                    'C' -> {index++; 90}
                    else -> 10
                }
                'V' ->  5
                'I' ->  when (nextChar) {
                    'V' -> {index++; 4}
                    'X' -> {index++; 9}
                    else -> 1
                }
                else -> 0
            }
            index++
        }
        return result
    }

    @Test
    fun test() {
        assert(romanToInt("III") == 3)
        assert(romanToInt("IV") == 4)
        assert(romanToInt("IX") == 9)
        assert(romanToInt("LVIII") == 58)
        assert(romanToInt("MCMXCIV") == 1994)
    }
}