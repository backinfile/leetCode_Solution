package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import com.backinfile.toIntList
import org.junit.Test

class `Solution_product-of-array-except-self` {
    fun productExceptSelf(nums: IntArray): IntArray {
        val left = IntArray(nums.size)
        val right = IntArray(nums.size)

        var cur = 1
        for (i in nums.indices) {
            left[i] = cur
            cur *= nums[i]
        }
        cur = 1
        for (i in nums.indices.reversed()) {
            right[i] = cur
            cur *= nums[i]
        }

        val result = IntArray(nums.size)
        for (i in nums.indices) {
            result[i] = left[i] * right[i]
        }
        return result
    }


    @Test
    fun test() {
        productExceptSelf("[1,2,3,4]".toIntArray()).toList() assertEqualTo "[24,12,8,6]".toIntList()
        productExceptSelf("[-1,1,0,-3,3]".toIntArray()).toList() assertEqualTo "[0,0,9,0,0]".toIntList()
    }
}