package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_house-robber` {

    fun rob(nums: IntArray): Int {
        when (nums.size) {
            0 -> return 0
            1 -> return nums[0]
            2 -> return Math.max(nums[0], nums[1])
        }

        var lastLastLast = nums[0] // 表示当选取上上上家时的最大值
        var lastLast = nums[1]  // 表示当选取上上家时的最大值
        var last = nums[2] + nums[0] // 表示当选取上一家时最大值

        for (i in 3 until nums.size) {
            last = last.coerceAtLeast(lastLast + nums[i]).coerceAtLeast(lastLastLast + nums[i])
                .also { lastLastLast = lastLast; lastLast = last }
        }
        return last.coerceAtLeast(lastLast).coerceAtLeast(lastLastLast)
    }

    @Test
    fun test() {
        assert(4 == rob("[1,2,3,1]".toIntArray()))
        assert(12 == rob("[2,7,9,3,1]".toIntArray()))
        assert(4 == rob("[2,1,1,2]".toIntArray()))
        assert(5 == rob("[2,1,1,1,2]".toIntArray()))
    }
}