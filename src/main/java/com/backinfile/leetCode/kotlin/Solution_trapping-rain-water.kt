package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test
import java.util.Stack

class `Solution_trapping-rain-water` {
    fun trap(height: IntArray): Int {
        if (height.size <= 2) {
            return 0
        }
        var sum = 0

        var maxIndex = 0
        var maxHeight = height[0]
        height.forEachIndexed { index, h ->
            if (h > maxHeight) {
                maxIndex = index; maxHeight = h
            }
        }

        var curHeight = 0
        for (i in 0 until maxIndex) {
            val h = height[i]
            if (h > curHeight) {
                curHeight = h
            } else {
                sum += curHeight - h
            }
        }

        curHeight = 0
        for (i in height.lastIndex downTo maxIndex + 1) {
            val h = height[i]
            if (h > curHeight) {
                curHeight = h
            } else {
                sum += curHeight - h
            }
        }
        return sum
    }

    @Test
    fun test() {
        assert(testFunc("[0,1,0,2,1,0,1,3,2,1,2,1]", 6))
        assert(testFunc("[4,2,3]", 1))
        assert(testFunc("[4,2,0,3,2,5]", 9))
    }

    private fun testFunc(inputStr: String, answer: Int): Boolean {
        val output = trap(Utils.toIntArray(inputStr))
        println(output)
        return output == answer
    }
}