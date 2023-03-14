package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_subarray-sum-equals-k_2` {

    // 前缀和
    fun subarraySum(nums: IntArray, k: Int): Int {
        val map = hashMapOf(0 to 1) // 前缀
        var result = 0

        // 答案前缀 + k = cur
        // 所以 答案前缀 = cur - k
        var cur = 0
        for (i in nums.indices) {
            cur += nums[i]
            result += map[cur - k] ?: 0
            map.compute(cur) { _, o -> (o ?: 0) + 1 }
        }
        return result
    }

    @Test
    fun test() {
        2 assertEqualTo subarraySum("[1,1,1]".toIntArray(), 2)
        2 assertEqualTo subarraySum("[1,2,3]".toIntArray(), 3)
    }
}