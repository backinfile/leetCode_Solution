package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_maximum-product-subarray` {
    fun maxProduct(nums: IntArray): Int {
        var minValue = nums[0]
        var maxValue = nums[0]
        var result = nums[0]
        for (i in 1 until nums.size) {
            val n = nums[i]
            maxValue = Math.max(n, Math.max(maxValue * n, minValue * n)).also {
                minValue = Math.min(n, Math.min(maxValue * n, minValue * n))
            }
            result = result.coerceAtLeast(maxValue)
        }
        return result
    }

    @Test
    fun test() {
        assert(12 == maxProduct("[-4,-3,-2]".toIntArray()))
        assert(6 == maxProduct("[2,3,-2,4]".toIntArray()))
        assert(0 == maxProduct("[-2,0,-1]".toIntArray()))
    }
}