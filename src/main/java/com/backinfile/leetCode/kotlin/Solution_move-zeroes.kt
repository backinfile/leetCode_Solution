package com.backinfile.leetCode.kotlin

class `Solution_move-zeroes` {
    fun moveZeroes(nums: IntArray): Unit {
        var left = 0
        for (i in nums.indices) {
            if (nums[i] == 0) {
                continue
            }
            nums[left++] = nums[i]
        }
        nums.fill(0, left)
    }
}