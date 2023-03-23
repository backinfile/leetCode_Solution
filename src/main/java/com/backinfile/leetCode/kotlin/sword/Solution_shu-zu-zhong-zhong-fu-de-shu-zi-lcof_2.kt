package com.backinfile.leetCode.kotlin.sword

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_shu-zu-zhong-zhong-fu-de-shu-zi-lcof_2` {
    fun findRepeatNumber(nums: IntArray): Int {
        val size = nums.size
        for (i in nums.indices) {
            val curValue = if (nums[i] < 0) nums[i] + size else nums[i]
            if (nums[curValue] < 0) {
                return curValue
            }
            nums[curValue] -= size
        }
        return -1
    }

    @Test
    fun test() {
        1 assertEqualTo findRepeatNumber("[3,4,2,1,1,0]".toIntArray())
        assert(findRepeatNumber("[2, 3, 1, 0, 2, 5, 3]".toIntArray()) in intArrayOf(2, 3))
    }
}