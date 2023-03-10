package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.assertSortedEqualTo
import com.backinfile.toStrList
import org.junit.Test

class `Solution_remove-invalid-parentheses_2` {
    fun removeInvalidParentheses(s: String): List<String> {
        val stack = java.util.Stack<Char>()
        val skips = BooleanArray(s.length) { false }

        val result = hashSetOf<String>()
        var maxLength = Int.MIN_VALUE

        fun dfs(index: Int, skipCnt: Int) {

            if (index >= s.length) {
                if (stack.isEmpty()) {
                    val len = s.length - skipCnt
                    if (len < maxLength) { // 长度较短的result直接舍弃
                        return
                    }
                    if (len > maxLength) { // 刷新最终答案长度，弃掉所有短于result的
                        result.clear();
                        maxLength = len;
                    }
                    result.add(s.asSequence().filterIndexed { i, _ -> !skips[i] }.joinToString(""))
                }
                return
            }


            when (s[index]) {
                '(' -> {
                    stack.add('(')
                    dfs(index + 1, skipCnt)
                    stack.pop()
                }

                ')' -> {
                    if (stack.isNotEmpty() && stack.last() == '(') {
                        stack.pop()
                        dfs(index + 1, skipCnt)
                        stack.push('(')
                    }
                }

                else -> {
                    dfs(index + 1, skipCnt)
                    return
                }
            }
            skips[index] = true
            dfs(index + 1, skipCnt + 1)
            skips[index] = false
        }
        dfs(0, 0)
        return result.toList()
    }

    @Test
    fun test() {
        "[\"(())()\",\"()()()\"]".toStrList() assertSortedEqualTo removeInvalidParentheses("()())()")
        "[\"(a())()\",\"(a)()()\"]".toStrList() assertSortedEqualTo removeInvalidParentheses("(a)())()")
        "[\"\"]".toStrList() assertSortedEqualTo removeInvalidParentheses(")(")
    }
}