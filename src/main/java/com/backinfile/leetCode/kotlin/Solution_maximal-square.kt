package com.backinfile.leetCode.kotlin

import com.backinfile.toCharArrayArray
import org.junit.Test

class `Solution_maximal-square` {

    // 同最大矩形做法
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val width = matrix[0].size
        val heights = IntArray(width)
        val stack = java.util.Stack<Int>()
        var result = 0

        fun Int.height(): Int {
            return heights[this]
        }

        fun pushHeight(curHeight: Int, index: Int) {
            while (stack.isNotEmpty() && stack.peek().height() > curHeight) {
                val maxHeightIndex = stack.pop()
                val lastIndex = if (stack.isEmpty()) -1 else stack.peek()
                val maxHeight = maxHeightIndex.height().coerceAtMost(index - lastIndex - 1)
                result = result.coerceAtLeast(maxHeight * maxHeight)
            }
            stack.add(index)
        }

        for (h in matrix.indices) {
            stack.clear()
            for (w in matrix[0].indices) {
                heights[w] = if (matrix[h][w] == '1') heights[w] + 1 else 0
                pushHeight(heights[w], w)
            }
            pushHeight(0, width)
        }
        return result
    }


    @Test
    fun test() {
        assert(4 == maximalSquare("[[\"1\",\"0\",\"1\",\"0\"],[\"1\",\"0\",\"1\",\"1\"],[\"1\",\"0\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\"]]".toCharArrayArray()))


        assert(4 == maximalSquare("[[\"1\",\"0\",\"1\",\"0\",\"0\"],[\"1\",\"0\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"0\",\"0\",\"1\",\"0\"]]".toCharArrayArray()))
        assert(1 == maximalSquare("[[\"0\",\"1\"],[\"1\",\"0\"]]".toCharArrayArray()))
        assert(0 == maximalSquare("[[\"0\"]]".toCharArrayArray()))
    }
}