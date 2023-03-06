package com.backinfile.leetCode.kotlin

import java.util.*

class `Solution_valid-parentheses` {
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        for (ch in s) {
            when (ch) {
                '[', '(', '{' -> stack.add(ch)
                ']' -> {
                    if (stack.isEmpty()) return false
                    if (stack.pop() != '[') return false
                }
                ')' -> {
                    if (stack.isEmpty()) return false
                    if (stack.pop() != '(') return false
                }
                '}' -> {
                    if (stack.isEmpty()) return false
                    if (stack.pop() != '{') return false
                }
            }
        }
        return stack.isEmpty()
    }
}