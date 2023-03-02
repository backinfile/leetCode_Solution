package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_largest-rectangle-in-histogram_3` {
    fun largestRectangleArea(heights: IntArray): Int {
        var result = 0
        val stack = ArrayDeque<Pair<Int, Int>>() // index->bottomIndex
        val stack2 = ArrayDeque<Pair<Int, Int>>()

        for (i in heights.indices) {
            println("curHeight=$result i=${i - 1}")
            val curHeight = heights[i]
            if (stack.isEmpty()) {
                result = result.coerceAtLeast(curHeight)
                stack.addLast(i to i)
                continue
            }

            val lastHeight = heights[stack.last().first]
            if (curHeight <= lastHeight) {
                val bottomIndex = stack.removeLast().second
                stack.addLast(i to bottomIndex)

                result = result.coerceAtLeast(curHeight * (i - bottomIndex + 1))
            } else {
                result = result.coerceAtLeast(heights[i])

                val minIndex = stack.last().first
                var curLastBottomIndex = i
                do {
                    val last = stack.removeLast()
                    val (index, bottomIndex) = last
                    if (index < minIndex) {
                        continue
                    }
                    if (curHeight == heights[index]) {
                        curLastBottomIndex = curLastBottomIndex.coerceAtMost(bottomIndex)
                    } else {
                        stack2.addLast(last)
                    }
                    result = result.coerceAtLeast(heights[index] * (i - bottomIndex + 1))
                } while (!stack.isEmpty() && curHeight >= heights[stack.last().first])
                stack.add(i to curLastBottomIndex)
                while (!stack2.isEmpty()) stack.addLast(stack2.removeLast())
            }
        }

        println(result)
        return result
    }

    @Test
    fun test() {
        assert(6 == largestRectangleArea("[4,2,0,3,2,5]".toIntArray()))

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
        assert(30 == largestRectangleArea("[10,10,10]".toIntArray()))
    }
}