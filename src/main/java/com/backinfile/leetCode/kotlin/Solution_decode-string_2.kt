package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_decode-string_2` {
    fun decodeString(s: String): String {
        val strStack = java.util.Stack<StringBuilder>()
        val repeatStack = java.util.Stack<Int>()

        var sb = StringBuilder()
        var repeatNum = 0

        for (ch in s) {
            if (ch == '[') {
                strStack.push(sb).also { sb = StringBuilder() }
                repeatStack.push(repeatNum).also { repeatNum = 0 }
            } else if (ch == ']') {
                val parent = strStack.pop()
                repeat(repeatStack.pop()) { parent.append(sb) }.also { sb = parent }
            } else if (Character.isDigit(ch)) {
                repeatNum = repeatNum * 10 + (ch - '0')
            } else {
                sb.append(ch)
            }
        }
        return sb.toString()
    }

    @Test
    fun test() {
        "aaabcbc" assertEqualTo decodeString("3[a]2[bc]")
        "accaccacc" assertEqualTo decodeString("3[a2[c]]")
        "abcabccdcdcdef" assertEqualTo decodeString("2[abc]3[cd]ef")
        "abccdcdcdxyz" assertEqualTo decodeString("abc3[cd]xyz")
    }
}