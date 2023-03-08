package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toIntArray
import org.junit.Test
import kotlin.random.Random

class `Solution_majority-element_2` {

    // 不同的元素两两抵消，剩下的就是最多的（超过一半的）
    fun majorityElement(nums: IntArray): Int {
        var result: Int = nums[0]
        var count = 0
        for (n in nums) {
            if (count == 0) {
                result = n
            }
            count += if (result == n) 1 else -1
        }
        return result
    }


    @Test
    fun test() {
        assert(3 == majorityElement("[3,2,3]".toIntArray()))
        assert(2 == majorityElement("[2,2,1,1,1,2,2]".toIntArray()))
        assert(2333 == majorityElement(Utils.readResource("input_majority-element_01.txt").toIntArray()))
    }
}