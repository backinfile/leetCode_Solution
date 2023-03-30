package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_jump-game-vi_2` {

    fun maxResult(nums: IntArray, k: Int): Int {

        val dp = IntArray(nums.size)
        val queue = java.util.ArrayDeque<Int>(k) // 存放数字index

        fun Int.num(): Int {
            return dp[this]
        }

        for (i in nums.indices) {
            if (i == 0) {
                dp[i] = nums[i]
                queue.add(i)
                continue
            }
            dp[i] = nums[i] + dp[queue.first]

            // 保持序列递减特性
            while (queue.isNotEmpty() && queue.last.num() <= i.num()) {
                queue.removeLast()
            }
            queue.addLast(i)

            // 删除超出区域长的元素
            while (queue.isNotEmpty() && queue.first <= i - k) {
                queue.removeFirst()
            }
        }
        return dp[nums.lastIndex]
    }


    @Test
    fun test() {
        0 assertEqualTo maxResult(" [1,-5,-20,4,-1,3,-6,-3]".toIntArray(), 2)
        7 assertEqualTo maxResult("[1,-1,-2,4,-7,3]".toIntArray(), 2)
        17 assertEqualTo maxResult("[10,-5,-2,4,0,3]".toIntArray(), 3)
    }
}