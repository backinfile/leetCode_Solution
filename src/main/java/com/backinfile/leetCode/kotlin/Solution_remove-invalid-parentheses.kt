package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.assertSortedEqualTo
import com.backinfile.toStrList
import org.junit.Test
import kotlin.math.max

class `Solution_remove-invalid-parentheses` {
    // 在方法二的基础上添加剪枝
    fun removeInvalidParentheses(s: String): List<String> {
        val stack = java.util.Stack<Char>()
        val sb = StringBuilder()

        val result = hashSetOf<String>()
        var maxLength = -1

        // 查找最多删除的括号数目，进行剪枝
        var left = 0
        var right = 0
        for (ch in s) {
            when (ch) {
                '(' -> left++
                ')' -> if (left > 0) left-- else right++
            }
        }



        fun dfs(index: Int, leftRemoved: Int, rightRemoved: Int) {
            val curRemoveCnt = index - sb.length
            val maxRemoveCnt = s.length - maxLength
            if (curRemoveCnt > maxRemoveCnt) { // 剪枝
                return
            }

            if (leftRemoved > left || rightRemoved > right) {
                return
            }

            if (index >= s.length) {
                if (stack.isEmpty()) {
                    val len = sb.length
                    if (len < maxLength) { // 长度较短的result直接舍弃
                        return
                    }
                    if (len > maxLength) { // 刷新最终答案长度，弃掉所有短于result的
                        result.clear();
                        maxLength = len;
                    }
                    result.add(sb.toString())
                }
                return
            }


            val ch = s[index]
            when (ch) {
                '(' -> {
                    stack.add('(')
                    sb.append('(')
                    dfs(index + 1, leftRemoved, rightRemoved)
                    stack.pop()
                    sb.setLength(sb.length - 1)
                }

                ')' -> {
                    if (stack.isNotEmpty() && stack.last() == '(') {
                        stack.pop()
                        sb.append(')')
                        dfs(index + 1, leftRemoved, rightRemoved)
                        stack.push('(')
                        sb.setLength(sb.length - 1)
                    }
                }

                else -> {
                    sb.append(ch)
                    dfs(index + 1, leftRemoved, rightRemoved)
                    sb.setLength(sb.length - 1)
                    return
                }
            }
            dfs(index + 1,
                leftRemoved + if (ch == '(') 1 else 0,
                rightRemoved + if (ch == ')') 1 else 0)
        }
        dfs(0, 0, 0)
        return result.toList()
    }

    @Test
    fun test() {
        "[\"(())()\",\"()()()\"]".toStrList() assertSortedEqualTo removeInvalidParentheses("()())()")
        "[\"(a())()\",\"(a)()()\"]".toStrList() assertSortedEqualTo removeInvalidParentheses("(a)())()")
        "[\"\"]".toStrList() assertSortedEqualTo removeInvalidParentheses(")(")
    }
}