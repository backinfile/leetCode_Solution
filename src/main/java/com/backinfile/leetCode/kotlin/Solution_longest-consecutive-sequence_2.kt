package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_longest-consecutive-sequence_2` {

    fun longestConsecutive(nums: IntArray): Int {
        val hashSet = hashSetOf<Int>()
        for (num in nums) {
            hashSet.add(num)
        }

        var result = 0
        for (num in hashSet) {
            if (num - 1 in hashSet) {
                continue
            }
            var size = 1
            while (num + size in hashSet) {
                size++
            }
            result = result.coerceAtLeast(size)
        }
        return result
    }


    @Test
    fun test() {
        assert(9 == longestConsecutive("[0,3,7,2,5,8,4,6,0,1]".toIntArray()))
        assert(4 == longestConsecutive("[100,4,200,1,3,2]".toIntArray()))
        assert(0 == longestConsecutive("[]".toIntArray()))
        assert(1 == longestConsecutive("[-1]".toIntArray()))
    }
}