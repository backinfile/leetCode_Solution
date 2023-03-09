package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_longest-increasing-subsequence_TLE` {
    fun lengthOfLIS(nums: IntArray): Int {

        fun dfs(_index: Int = 0, min: Int = Int.MIN_VALUE): Int {
            var index = _index
            while (index < nums.size &&  nums[index] <= min) {
                index++
            }
            if (index >= nums.size) {
                return 0
            }

            val cur = nums[index]
            return Math.max(
                if (cur > min) dfs(index + 1, cur) + 1 else 0,
                dfs(index + 1, min)
            )
        }
        return dfs(0, Int.MIN_VALUE)
    }

    @Test
    fun test() {
        4 assertEqualTo lengthOfLIS("[10,9,2,5,3,7,101,18]".toIntArray())
        4 assertEqualTo lengthOfLIS("[0,1,0,3,2,3]".toIntArray())
        1 assertEqualTo lengthOfLIS("[7,7,7,7,7,7,7]".toIntArray())
    }
}