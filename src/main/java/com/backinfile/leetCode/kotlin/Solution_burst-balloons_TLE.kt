package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_burst-balloons_TLE` {

    fun maxCoins(nums: IntArray): Int {
        val visited = BooleanArray(nums.size) { false }

        fun getScore(index: Int): Int {
            val mid = nums[index]
            var leftIndex = index - 1
            while (leftIndex >= 0 && !visited[leftIndex]) leftIndex--
            var rightIndex = index + 1
            while (rightIndex < nums.size && !visited[rightIndex]) rightIndex++

            val left = if (leftIndex < 0) 1 else nums[leftIndex]
            val right = if (rightIndex >= nums.size) 1 else nums[rightIndex]
            return left * mid * right
        }

        var result = 0
        fun dfs(cnt: Int, score: Int) {
            if (cnt == nums.size) {
                result = maxOf(result, score)
                return
            }
            for (i in nums.indices) {
                if (visited[i]) continue
                visited[i] = true
                dfs(cnt + 1, score + getScore(i))
                visited[i] = false
            }
        }
        dfs(0, 0)
        return result
    }

    @Test
    fun test() {
        167 assertEqualTo maxCoins("[3,1,5,8]".toIntArray())
        10 assertEqualTo maxCoins("[1,5]".toIntArray())
    }
}