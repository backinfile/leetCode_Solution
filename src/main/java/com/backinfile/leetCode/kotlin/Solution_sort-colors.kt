package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_sort-colors` {

    private fun IntArray.swap(a: Int, b: Int) =
        if (a != b) this[a] = this[b].also { this[b] = this[a] }
        else Unit

    fun sortColors(nums: IntArray): Unit {
        if (nums.size <= 1) {
            return
        }
        var left = 0
        var right = nums.lastIndex

        var index = 0
        while (index <= right) {
            when (nums[index]) {
                0 -> if (index > left) nums.swap(left++, index) else index++
                2 -> nums.swap(right--, index)
                1 -> index++
            }
        }
    }

    @Test
    fun test() {
        assert(testFunc("[1,2,0]"))
        assert(testFunc("[2,0,2,1,1,0]"))
        assert(testFunc("[2,0,1]"))
        assert(testFunc("[0,0,0]"))
        assert(testFunc("[1,1,1]"))
        assert(testFunc("[2,2,2]"))
        assert(testFunc("[]"))
        assert(testFunc("[2]"))
        assert(testFunc("[1]"))
    }

    private fun testFunc(inputStr: String): Boolean {
        val input = inputStr.toIntArray()
        val answer = input.sorted()
        sortColors(input)
        println(input.toList())
        return input.toList() == answer
    }
}