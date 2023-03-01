package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test
import kotlin.math.max

class `Solution_jump-game` {
    fun canJump(nums: IntArray): Boolean {
        var maxIndex = 0

        for (i in nums.indices) {
            if (i > maxIndex) {
                return false
            }
            maxIndex = Math.max(maxIndex, nums[i] + i)
        }
        return true
    }

    @Test
    fun test() {
        assert(testFunc("[2,3,1,1,4]", true))
        assert(testFunc("[3,2,1,0,4]", false))
    }

    private fun testFunc(inputStr: String, answer: Boolean): Boolean {
        val output = canJump(Utils.toIntArray(inputStr))
        println(answer)
        return answer == output

    }
}