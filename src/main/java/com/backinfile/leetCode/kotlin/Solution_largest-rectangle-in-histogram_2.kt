package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test
import java.util.stream.IntStream

class `Solution_largest-rectangle-in-histogram_2` {
    fun largestRectangleArea(heights: IntArray): Int {
        if (heights.isEmpty()) {
            return 0
        }
        var result = 0
        for (i in heights.indices) {
            var height = heights[i]
            for (j in i until heights.size) {
                height = height.coerceAtMost(heights[j])
                result = result.coerceAtLeast(height * (j - i + 1))
            }
        }
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