package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test
import kotlin.random.Random

class `Solution_majority-element` {

    // 随机
    fun majorityElement(nums: IntArray): Int {
        return (0..Int.MAX_VALUE).asSequence()
            .map { nums[Random.nextInt(nums.size)] }
            .map { num -> num to nums.count { it == num } }
            .filter { it.second > nums.size / 2 }
            .first().first
    }


    @Test
    fun test() {
        assert(3 == majorityElement("[3,2,3]".toIntArray()))
        assert(2 == majorityElement("[2,2,1,1,1,2,2]".toIntArray()))
    }
}