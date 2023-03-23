package com.backinfile.leetCode.kotlin.sword

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_shu-zu-zhong-zhong-fu-de-shu-zi-lcof` {
    fun findRepeatNumber(nums: IntArray): Int {
        for (i in nums.indices) {
            while (i != nums[i]) {
                val curValue = nums[i]
                if (nums[curValue] == curValue) {
                    return curValue
                }
                nums.swap(i, curValue)
            }
        }
        return -1
    }

    private fun IntArray.swap(a: Int, b: Int) {
        if (a != b) this[a] = this[b].also { this[b] = this[a] }
    }

    @Test
    fun test() {
        1 assertEqualTo findRepeatNumber("[3,4,2,1,1,0]".toIntArray())
        assert(findRepeatNumber("[2, 3, 1, 0, 2, 5, 3]".toIntArray()) in intArrayOf(2, 3))
    }
}