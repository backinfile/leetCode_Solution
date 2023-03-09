package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test
import kotlin.random.Random


class `Solution_find-the-duplicate-number` {
    fun findDuplicate(nums: IntArray): Int {
        var left = 1
        var right = nums.size - 1
        while (left <= right) {
            val mid = (left + right) / 2

            val cnt = nums.count { it <= mid }

            if (cnt <= mid) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

    @Test
    fun test() {
        2 assertEqualTo findDuplicate("[1,3,4,2,2]".toIntArray())
        3 assertEqualTo findDuplicate("[3,1,3,4,2]".toIntArray())
        2 assertEqualTo findDuplicate("[2,2,2,2,2]".toIntArray())
    }
}