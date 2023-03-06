package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_longest-consecutive-sequence` {

    fun longestConsecutive(nums: IntArray): Int {
        val hashMap = hashMapOf<Int, Int>()

        var result = 0
        for (num in nums) {
            if (num in hashMap) {
                continue
            }
            val lastValue = hashMap[num - 1]
            val nextValue = hashMap[num + 1]
            if (nextValue != null && lastValue != null) {
                result = result.coerceAtLeast(nextValue - lastValue + 1)
                hashMap[num] = num
                hashMap[nextValue] = lastValue
                hashMap[lastValue] = nextValue
            } else if (nextValue != null) {
                hashMap[num] = nextValue
                hashMap[nextValue] = num
                result = result.coerceAtLeast(nextValue - num + 1)
            } else if (lastValue != null) {
                hashMap[num] = lastValue
                hashMap[lastValue] = num
                result = result.coerceAtLeast(num - lastValue + 1)
            } else {
                hashMap[num] = num
                result = result.coerceAtLeast(1)
            }
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