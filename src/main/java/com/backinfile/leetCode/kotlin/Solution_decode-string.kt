package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_decode-string` {
    fun decodeString(s: String): String {
        fun decode(from: Int, to: Int, repeat: Int, result: StringBuilder): Int {
            var i = from
            val sb = StringBuilder()
            var repeatNext = 0
            while (i <= to) {
                val ch = s[i]
                if (ch == '[') {
                    i = decode(i + 1, to, repeatNext, sb)
                    repeatNext = 0
                } else if (ch == ']') {
                    break
                } else if (Character.isDigit(ch)) {
                    repeatNext = repeatNext * 10 + (ch - '0')
                } else {
                    sb.append(ch)
                }
                i++
            }
            repeat(repeat) { result.append(sb) }
            return i
        }


        val result = StringBuilder()
        decode(0, s.lastIndex, 1, result)
        return result.toString()
    }

    @Test
    fun test() {
        "aaabcbc" assertEqualTo decodeString("3[a]2[bc]")
        "accaccacc" assertEqualTo decodeString("3[a2[c]]")
        "abcabccdcdcdef" assertEqualTo decodeString("2[abc]3[cd]ef")
        "abccdcdcdxyz" assertEqualTo decodeString("abc3[cd]xyz")
    }
}