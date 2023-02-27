package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_next-permutation` {
    fun nextPermutation(nums: IntArray): Unit {
        if (nums.size <= 1) {
            return
        }

        var middleIndex = -1
        var lastValue = nums.last()
        for (index in nums.lastIndex - 1 downTo 0) {
            val curValue = nums[index]
            if (curValue < lastValue) {
                middleIndex = index
                break
            }
            lastValue = curValue
        }
        if (middleIndex == -1) {
            nums.reverse()
            return
        }


        run {
            val middleValue = nums[middleIndex]
            var lastIndex = nums.lastIndex
            while (nums[lastIndex] <= middleValue) lastIndex--

            nums[middleIndex] = nums[lastIndex]
            nums[lastIndex] = middleValue
        }
        reverse(nums, middleIndex + 1)
    }

    private fun reverse(nums: IntArray, from: Int) {
        for (i in nums.indices) {
            val curIndex = from + i
            val lastIndex = nums.size - 1 - i
            if (lastIndex <= curIndex) {
                break
            }
            val tmp = nums[curIndex]
            nums[curIndex] = nums[lastIndex]
            nums[lastIndex] = tmp
        }
    }

    @Test
    fun test() {
        assert(testFunc("[1,3,2]", "[2,1,3]"))
        assert(testFunc("[1,2,3]", "[1,3,2]"))
        assert(testFunc("[3,2,1]", "[1,2,3]"))
        assert(testFunc("[1,4,3,2]", "[2,1,3,4]"))
        assert(testFunc("[1,4,2,3]", "[1,4,3,2]"))


        assert(testFunc("[1,1,5]", "[1,5,1]"))
        assert(testFunc("[1,5,1]", "[5,1,1]"))
        assert(testFunc("[5,1,1]", "[1,1,5]"))
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        val input = Utils.toIntArray(inputStr)
        nextPermutation(input)
        println(input.toList())
        return (input.toList() == Utils.toIntArray(answerStr).toList())
    }
}