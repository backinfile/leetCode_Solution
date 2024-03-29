package com.backinfile.leetCode.kotlin

import org.junit.Test
import java.util.*

class `Solution_longest-valid-parentheses` {

    fun longestValidParentheses(s: String): Int {
        var maxLength = 0
        var index = 0
        var lastLength = 0
        while (index < s.length) {

            val next = find(s, index)
            if (next != -1) {
                lastLength += next - index + 1
                maxLength = maxLength.coerceAtLeast(lastLength)
                index = next + 1
            } else {
                index++
                lastLength = 0
            }
        }
        println(maxLength)
        return maxLength
    }


    private fun find(s: String, from: Int): Int {
        if (s[from] == ')') {
            return -1
        }
        val stack = Stack<Char>()
        stack.add(s[from])
        for (i in from + 1 until s.length) {
            if (s[i] == ')') {
                stack.pop()
                if (stack.isEmpty()) {
                    return i
                }
            } else {
                stack.push(s[i])
            }
        }
        return -1
    }


    @Test
    fun test() {
//        assert(longestValidParentheses("(()") == 2)
//        assert(longestValidParentheses(")()())") == 4)
        assert(
            1144 == longestValidParentheses(
                "))()())())))()))(()(()))))(()()())))((()()((()))))))()((((())))((((((()())(((()))(())()))((())(()((((((())(()))()())()())))(((((()())()())((((()(((())((()(())(((()))()()())()(())((((((()()())()()((())(((())()())()(())()))()))())(())())((()()(((((()))(()))())(()))(((()())))((((()))((())))())((((((())))(()(()(())()()()()())())(()()())((())()(())((())))(()())(())(((((()()(())()((()))))()())()(())(()((((()(((((()))(()(())(((())))())))(()())))(()))()))(()(()))(((()(())(())()()()))()(((())(((()())(((())())(()))))()()))())()((()()()()))())((()))()()(()()(())(((()()()()(()()())()((()()()())())))()((((()()(((())()()((())))(())(()))))(()(()()()))())(())())))))((((()())((()))(()())((((()))())))))()(()(()))))()))())()(((()()()())()()))(()((())((()(()(((((((()))()())()(()()()((((()())))))))(())()()(()())(())())))((())))())()())))(()))()()))(())()()))(()())))(()((((()()(()()((()))()()))(((()))))))()(())(((())(())())())(((()))((()(()())()()))()))()))()((())(())(((()(((()()())))(((()(()(()())()))()))())))((()))))(((((()()(()())))()())))))((((()(()(((())(()((()(()()()()()(())()(()((()()(()))))()(()(())()()))))()))))()()()()(()()((()()()()()((())))()())(()(())(())(()(()())()()))(()))))(()())())()((()))())()())((((()(()()((())(()()()(((()((()(()())(((()((()(()(()(()((()((()())(((((())((()())())((())))))(((((((()(((((()(()))())((()(((((((()(((((()((()()()())(()(()(())))()(("
            )
        )
    }
}