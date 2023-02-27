package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_generate-parentheses` {

    fun generateParenthesis(n: Int): List<String> {
        val result = ArrayList<String>()
        dfs(n * 2, n, StringBuilder(n * 2), 0, 0, result)
        return result
    }

    private fun dfs(curIndex: Int, n: Int, cur: StringBuilder, left: Int, right: Int, result: ArrayList<String>) {
        if (left > n || right > n || right > left) {
            return
        }
        if (curIndex == 0) {
            result.add(cur.toString())
            return
        }

        val length = cur.length

        cur.append('(')
        dfs(curIndex - 1, n, cur, left + 1, right, result)

        cur.setLength(length)
        cur.append(')')
        dfs(curIndex - 1, n, cur, left, right + 1, result)
    }


    fun generateParenthesis_hasClip(n: Int): List<String> {
        val result = ArrayList<List<String>>()
        result.add(listOf(""));

        val unique = arrayListOf(0)

        for (i in 1..n) {
            val uniques = arrayListOf<String>()
            for (str in result[i - 1]) {
                uniques.add("($str)")
            }
            unique.add(uniques.size)
            val loopResult = hashSetOf<String>()

            for (left in 1 until i) {
                val right = i - left
                if (left == 1 || right == 1) {
                    for (leftStr in result[left]) {
                        for (rightStr in result[right]) {
                            loopResult.add(leftStr + rightStr)
                        }
                    }
                } else {
                    for (leftStr in result[left]) {
                        for (rIndex in 0 until unique[right]) {
                            loopResult.add(leftStr + result[right][rIndex])
                        }
                    }
                }
            }
            uniques.addAll(loopResult)
            result.add(uniques)
        }
        return result[n]
    }


    // 无剪枝
    fun generateParenthesis_noClip(n: Int): List<String> {
        val result = ArrayList<HashSet<String>>()
        result.add(hashSetOf(""));
        for (i in 1..n) {
            val loopResult = hashSetOf<String>()
            for (str in result[i - 1]) {
                loopResult.add("($str)")
            }
            for (left in 1 until i) {
                for (leftStr in result[left]) {
                    for (rightStr in result[i - left]) {
                        loopResult.add(leftStr + rightStr)
                    }
                }
            }
            result.add(loopResult)
        }
        return result[n].toList();
    }


    @Test
    fun test() {
        assert(
            equal(
                generateParenthesis(4),
                Utils.toStrArray("[\"(((())))\",\"((()()))\",\"((())())\",\"((()))()\",\"(()(()))\",\"(()()())\",\"(()())()\",\"(())(())\",\"(())()()\",\"()((()))\",\"()(()())\",\"()(())()\",\"()()(())\",\"()()()()\"]")
            )
        )
        assert(
            generateParenthesis(3).toSet() == Utils.toStrArray("[\"((()))\",\"(()())\",\"(())()\",\"()(())\",\"()()()\"]")
                .toSet()
        )
        assert(generateParenthesis(1).toSet() == Utils.toStrArray("[\"()\"]").toSet())
    }

    private fun equal(l1: List<String>, l2: Array<String>): Boolean {
        return l1.toSet() == l2.toSet()
    }
}