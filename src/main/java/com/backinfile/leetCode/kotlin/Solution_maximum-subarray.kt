package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_maximum-subarray` {
    fun maxSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var max = nums[0]
        var dp = max
        for (i in 1 until nums.size) {
            dp = nums[i] + if (dp > 0) dp else 0
            max = Math.max(max, dp)
        }
        return max
    }

    @Test
    fun test() {
        assert(testFunc("[-2,1,-3,4,-1,2,1,-5,4]", 6))
        assert(testFunc("[1]", 1))
        assert(testFunc("[5,4,-1,7,8]", 23))
    }

    private fun testFunc(inputStr: String, answer: Int): Boolean {
        val output = maxSubArray(Utils.toIntArray(inputStr))
        println(output)
        return output == answer
    }
}