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
            when (s[index]) {
                'M' -> result += 1000
                'D' -> result += 500
                'C' -> result += when(nextChar) {
                    'D' -> {index++; 400}
                    'M' -> {index++; 900}
                    else -> 100
                }
                'L' -> result += 50
                'X' -> result += when(nextChar) {
                    'L' -> {index++; 40}
                    'C' -> {index++; 90}
                    else -> 10
                }
                'V' -> result += 5
                'I' -> result += when(nextChar) {
                    'V' -> {index++; 4}
                    'X' -> {index++; 9}
                    else -> 1
                }
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