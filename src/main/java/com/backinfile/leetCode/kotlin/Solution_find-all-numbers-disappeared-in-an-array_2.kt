package com.backinfile.leetCode.kotlin

import com.backinfile.assertSortedEqualTo
import com.backinfile.toIntArray
import com.backinfile.toIntList
import org.junit.Test
import kotlin.math.abs

class `Solution_find-all-numbers-disappeared-in-an-array_2` {
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        for (i in nums.indices) {
            val exist = abs(nums[i]) - 1
            nums[exist] = -abs(nums[exist])
        }
        val result = arrayListOf<Int>()
        for (i in nums.indices) {
            if (nums[i] > 0) {
                result += i + 1
            }
        }
        return result
    }

    @Test
    fun test() {
        "[3]".toIntList() assertSortedEqualTo findDisappearedNumbers("[1,1,2,4]".toIntArray())
        "[5,6]".toIntList() assertSortedEqualTo findDisappearedNumbers("[4,3,2,7,8,2,3,1]".toIntArray())
        "[2]".toIntList() assertSortedEqualTo findDisappearedNumbers("[1,1]".toIntArray())
    }
}