package com.backinfile.leetCode.kotlin

import org.junit.Test
import kotlin.math.max

class `Solution_longest-valid-parentheses_TLE` {

    fun longestValidParentheses(s: String): Int {
        val dp = Array(s.length) { BooleanArray(s.length) }

        var maxLength = 0
        for (length in 2..s.length step 2) {
            for (left in s.indices) {
                val right = left + length - 1
                if (right >= s.length) {
                    continue
                }
                val leftValue = s[left]
                val rightValue = s[right]
                if (leftValue == '(' && rightValue == ')' && (length <= 2 || dp[left + 1][right - 1])) {
                } else if (length > 2 && (left + 1 until right - 1).any { dp[left][it] && dp[it + 1][right] }) {
                } else {
                    continue
                }

                dp[left][right] = true
                maxLength = max(maxLength, length)
            }
        }
        println(maxLength)
        return maxLength
    }

    @Test
    fun test() {
//        assert(longestValidParentheses("(()") == 2)
//        assert(longestValidParentheses(")()())") == 4)
        assert(1144 == longestValidParentheses("))()())())))()))(()(()))))(()()())))((()()((()))))))()((((())))((((((()())(((()))(())()))((())(()((((((())(()))()())()())))(((((()())()())((((()(((())((()(())(((()))()()())()(())((((((()()())()()((())(((())()())()(())()))()))())(())())((()()(((((()))(()))())(()))(((()())))((((()))((())))())((((((())))(()(()(())()()()()())())(()()())((())()(())((())))(()())(())(((((()()(())()((()))))()())()(())(()((((()(((((()))(()(())(((())))())))(()())))(()))()))(()(()))(((()(())(())()()()))()(((())(((()())(((())())(()))))()()))())()((()()()()))())((()))()()(()()(())(((()()()()(()()())()((()()()())())))()((((()()(((())()()((())))(())(()))))(()(()()()))())(())())))))((((()())((()))(()())((((()))())))))()(()(()))))()))())()(((()()()())()()))(()((())((()(()(((((((()))()())()(()()()((((()())))))))(())()()(()())(())())))((())))())()())))(()))()()))(())()()))(()())))(()((((()()(()()((()))()()))(((()))))))()(())(((())(())())())(((()))((()(()())()()))()))()))()((())(())(((()(((()()())))(((()(()(()())()))()))())))((()))))(((((()()(()())))()())))))((((()(()(((())(()((()(()()()()()(())()(()((()()(()))))()(()(())()()))))()))))()()()()(()()((()()()()()((())))()())(()(())(())(()(()())()()))(()))))(()())())()((()))())()())((((()(()()((())(()()()(((()((()(()())(((()((()(()(()(()((()((()())(((((())((()())())((())))))(((((((()(((((()(()))())((()(((((((()(((((()((()()()())(()(()(())))()(("))
    }
}