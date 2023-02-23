package com.backinfile.leetCode.kotlin

import org.junit.Test


class `Solution_integer-to-roman` {
    fun intToRoman(num: Int): String {
        if (num == 0) {
            return ""
        }
        return STRINGS[3][num / 1000] + STRINGS[2][(num / 100) % 10] + STRINGS[1][(num / 10) % 10] + STRINGS[0][num % 10]
    }

    companion object {
        val STRINGS = arrayOf(
            arrayOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"),
            arrayOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"),
            arrayOf("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"),
            arrayOf("", "M", "MM", "MMM")
        )

        val CHARS = arrayOf('I', 'V', 'X', 'L', 'C', 'D', 'M')
    }

    /**
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */

    @Test
    fun test() {
        assert(intToRoman(3) == "III")
        assert(intToRoman(4) == "IV")
        assert(intToRoman(9) == "IX")
        assert(intToRoman(58) == "LVIII")
        assert(intToRoman(1994) == "MCMXCIV")
    }
}