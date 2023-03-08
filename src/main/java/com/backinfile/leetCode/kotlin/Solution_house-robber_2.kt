package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_house-robber_2` {

    fun rob(nums: IntArray): Int {
        when (nums.size) {
            0 -> return 0
            1 -> return nums[0]
        }

        var lastLast = nums[0] // 表示上上次的最大值
        var last = Math.max(nums[1], nums[0])  // 表示上次的最大值

        for (i in 2 until nums.size) {
            last = last.coerceAtLeast(lastLast + nums[i])
                .also {lastLast = last }
        }
        return last.coerceAtLeast(lastLast)
    }

    @Test
    fun test() {
        assert(4 == rob("[1,2,3,1]".toIntArray()))
        assert(12 == rob("[2,7,9,3,1]".toIntArray()))
        assert(4 == rob("[2,1,1,2]".toIntArray()))
        assert(5 == rob("[2,1,1,1,2]".toIntArray()))
    }
}