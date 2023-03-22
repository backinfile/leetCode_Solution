package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof` {
    fun minArray(numbers: IntArray): Int {
        if (numbers.isEmpty()) return -1

        var left = 0
        var right = numbers.lastIndex
        while (left < right) {
            val mid = (left + right) ushr 1
            val midValue = numbers[mid]
            val rightValue = numbers[right]
            if (midValue < rightValue) {
                right = mid
            } else if (midValue > rightValue) {
                left = mid + 1
            } else {
                right--
            }
        }
        return numbers[left]
    }

    @Test
    fun test() {
        1 assertEqualTo minArray("[3,3,3,3,3,3,3,3,1,3]".toIntArray())
        1 assertEqualTo minArray("[1,10,10,10,10]".toIntArray())
        1 assertEqualTo minArray("[3,1,3]".toIntArray())
        1 assertEqualTo minArray("[3,3,1,3]".toIntArray())
        1 assertEqualTo minArray("[3,1,1]".toIntArray())
        1 assertEqualTo minArray("[3,4,5,1,2]".toIntArray())
        0 assertEqualTo minArray("[2,2,2,0,1]".toIntArray())
    }
}