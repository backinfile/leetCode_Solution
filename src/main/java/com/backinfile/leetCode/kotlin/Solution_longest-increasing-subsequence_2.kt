package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_longest-increasing-subsequence_2` {

    fun lengthOfLIS(nums: IntArray): Int {
        val dp = ArrayList<Int>(nums.size) // dp[i]记录依此为长度的数组最后一个值的最大值 递增队列
        dp@ for (i in nums.indices) {
            val cur = nums[i]
            if (dp.isEmpty() || dp.last() < cur) { // 新元素大于dp中的所有值，则最终长度+1
                dp.add(cur)
            } else {
                // 因为不可以重复，所有把第一个>=cur的位置减小至cur即可
                for (j in dp.size - 2 downTo 0) {
                    if (dp[j] < cur) {
                        dp[j + 1] = cur
                        continue@dp
                    }
                }
                dp[0] = cur
            }
        }
        return dp.size
    }

    @Test
    fun test() {
        4 assertEqualTo lengthOfLIS("[10,9,2,5,3,7,101,18]".toIntArray())
        4 assertEqualTo lengthOfLIS("[0,1,0,3,2,3]".toIntArray())
        1 assertEqualTo lengthOfLIS("[7,7,7,7,7,7,7]".toIntArray())
    }
}