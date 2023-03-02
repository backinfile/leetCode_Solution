package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_largest-rectangle-in-histogram_2` {
    fun largestRectangleArea(heights: IntArray): Int {
        var result = 0
        val stack = ArrayDeque<Int>()

        for (i in heights.indices) {
            val curHeight = heights[i]
            while (!stack.isEmpty() && heights[stack.last()] >= curHeight) {
                val index = stack.removeLast()
                val lastIndex = if (stack.isEmpty()) -1 else stack.last()
                result = result.coerceAtLeast(heights[index] * (i - lastIndex - 1))
            }
            stack.addLast(i)
        }
        if (!stack.isEmpty()) {
            var lastIndex = 0
            for (i in stack.indices) {
                result = result.coerceAtLeast(heights[stack[i]] * (heights.lastIndex - lastIndex + 1))
                lastIndex = stack[i] + 1
            }
        }
        return result
    }

    @Test
    fun test() {
        assert(6 == largestRectangleArea("[4,2,0,3,2,5]".toIntArray()))

        assert(14 == largestRectangleArea("[4,5,3,2,7,5,3,0]".toIntArray()))
        assert(14 == largestRectangleArea("[6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3]".toIntArray()))
        assert(20 == largestRectangleArea("[3,6,5,7,4,8,1,0]".toIntArray()))
        assert(30 == largestRectangleArea("[10,10,10]".toIntArray()))

        assert(9 == largestRectangleArea("[1,2,3,4,5]".toIntArray()))
        assert(27 == largestRectangleArea("[1,9,9,9,1,10,10]".toIntArray()))
        assert(27 == largestRectangleArea("[9,12,13,0,10,10]".toIntArray()))
        assert(10 == largestRectangleArea("[10,0,10]".toIntArray()))
        assert(10 == largestRectangleArea("[9,0,10]".toIntArray()))
        assert(20 == largestRectangleArea("[9,0,10,10]".toIntArray()))

        assert(10 == largestRectangleArea("[2,1,5,6,2,3]".toIntArray()))
        assert(4 == largestRectangleArea("[2,4]".toIntArray()))
        assert(6 == largestRectangleArea("[2,4,3]".toIntArray()))
        assert(2 == largestRectangleArea("[2]".toIntArray()))

        assert(2 == largestRectangleArea("[2]".toIntArray()))
    }
}