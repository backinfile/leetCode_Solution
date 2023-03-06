package com.backinfile.leetCode.kotlin

class `Solution_single-number` {
    fun singleNumber(nums: IntArray): Int {
        var result = nums[0]
        for(i in 1..nums.lastIndex) {
            result = result xor nums[i]
        }
        return result
    }
}