package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test
import kotlin.random.Random


class `Solution_find-the-duplicate-number_3` {

    // 环的判定 无额外空间
    fun findDuplicate(nums: IntArray): Int {

        val head = 0
        var slow = nums[head]
        var fast = nums[nums[head]]

        while (slow != fast) {
            slow = nums[slow]
            fast = nums[nums[fast]]
        }

        var chase = head
        while (chase != slow) {
            chase = nums[chase]
            slow = nums[slow]
        }
        return chase
    }

    @Test
    fun test() {
        2 assertEqualTo findDuplicate("[1,3,4,2,2]".toIntArray())
        3 assertEqualTo findDuplicate("[3,1,3,4,2]".toIntArray())
        2 assertEqualTo findDuplicate("[2,2,2,2,2]".toIntArray())
        20 assertEqualTo findDuplicate(((1..10000).toList() + listOf(20)).toIntArray())
    }
}