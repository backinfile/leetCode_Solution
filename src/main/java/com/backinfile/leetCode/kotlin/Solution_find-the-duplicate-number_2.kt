package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test
import kotlin.random.Random


class `Solution_find-the-duplicate-number_2` {

    // 环的判定 有额外空间
    fun findDuplicate(nums: IntArray): Int {

        val path = BooleanArray(nums.size) { false }
        fun dfs(n: Int): Int {
            if (path[n]) {
                return n
            }
            path[n] = true
            return dfs(nums[n])
        }
        return dfs(0)
    }

    @Test
    fun test() {
        2 assertEqualTo findDuplicate("[1,3,4,2,2]".toIntArray())
        3 assertEqualTo findDuplicate("[3,1,3,4,2]".toIntArray())
        2 assertEqualTo findDuplicate("[2,2,2,2,2]".toIntArray())
        20 assertEqualTo findDuplicate(((1..10000).toList() + listOf(20)).toIntArray())
    }
}