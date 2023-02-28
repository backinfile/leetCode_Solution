package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_trapping-rain-water_2` {
    fun trap(height: IntArray): Int {
        if (height.size <= 2) {
            return 0
        }
        var sum = 0
        var left = 0
        var right = height.lastIndex

        var leftMaxHeight = 0
        var rightMaxHeight = 0

        var leftHeight = height[left]
        var rightHeight = height[right]

        while (left < right) {
            if (leftHeight < rightHeight) {
                if (leftHeight > leftMaxHeight) {
                    leftMaxHeight = leftHeight
                } else {
                    sum += leftMaxHeight - leftHeight
                }
                leftHeight = height[++left]
            } else {
                if (rightHeight > rightMaxHeight) {
                    rightMaxHeight = rightHeight
                } else {
                    sum += rightMaxHeight - rightHeight
                }
                rightHeight = height[--right]
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