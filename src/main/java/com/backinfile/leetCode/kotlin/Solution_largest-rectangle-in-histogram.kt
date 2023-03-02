package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_largest-rectangle-in-histogram` {
    fun largestRectangleArea(heights: IntArray): Int {
        var result = 0
        val stack = ArrayDeque<Pair<Int, Int>>() // index->bottomIndex

        for (i in heights.indices) {
//            println("curHeight=$result i=${i - 1}")
            val curHeight = heights[i]
            if (stack.isEmpty()) {
                result = result.coerceAtLeast(curHeight)
                stack.addLast(i to i)
                continue
            }

            var bottomIndex: Int = -1
            while (curHeight <= heights[stack.last().first]) {
                bottomIndex = stack.removeLast().second
                if (stack.isEmpty()) break
            }
            if (bottomIndex != -1) {
                result = result.coerceAtLeast(curHeight * (i - bottomIndex + 1))
                stack.addLast(i to bottomIndex)
            }

            for (last in stack) {
                result = result.coerceAtLeast(heights[last.first] * (i - last.second + 1))
            }

            if (bottomIndex == -1) {
                result = result.coerceAtLeast(curHeight)
                stack.addLast(i to i)
            }
        }


//        println(result)
        return result
    }

    @Test
    fun test() {
        assert(14 == largestRectangleArea("[4,5,3,2,7,5,3]".toIntArray()))
        assert(14 == largestRectangleArea("[6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3]".toIntArray()))
        assert(20 == largestRectangleArea("[3,6,5,7,4,8,1,0]".toIntArray()))
        assert(30 == largestRectangleArea("[10,10,10]".toIntArray()))
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
    }
}