package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_subarray-sum-equals-k` {
    fun subarraySum(nums: IntArray, k: Int): Int {
        val left = IntArray(nums.size)
        val right = IntArray(nums.size)
        var temp = 0
        for (i in nums.indices) {
            left[i] = temp
            temp += nums[i]
        }
        temp = 0
        for (i in nums.indices.reversed()) {
            right[i] = temp
            temp += nums[i]
        }
        val sum = nums.sum()
        var result = 0

        for (i in nums.indices) {
            for (j in i until nums.size) {
                if (sum - left[i] - right[j] == k) {
                    result++
                }
            }
        }
        return result
    }

    @Test
    fun test() {
        2 assertEqualTo subarraySum("[1,1,1]".toIntArray(), 2)
        2 assertEqualTo subarraySum("[1,2,3]".toIntArray(), 3)
    }
}