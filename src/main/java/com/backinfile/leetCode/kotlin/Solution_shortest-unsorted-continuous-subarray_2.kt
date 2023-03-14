package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_shortest-unsorted-continuous-subarray_2` {


    // nums划分左中右三段，中段为答案
    // 则有左端<=中段<=右端
    fun findUnsortedSubarray(nums: IntArray): Int {
        if (nums.size <= 1) {
            return 0
        }
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE

        var left = -1
        var right = -1

        // 找递增序列的起点(中段与右端分界点)
        for (i in nums.indices) {
            val n = nums[i]
            if (n < max) { // 比当前最大值还小，说明不可能是递增序列，设置right
                right = i
            }
            max = maxOf(max, n)
        }

        // 找递减序列的起点(左端与中段分界点)
        for (i in nums.indices.reversed()) {
            val n = nums[i]
            if (n > min) { // 比最小值大，说明不是递减序列，设置left
                left = i
            }
            min = minOf(min, n)
        }
        return if (right == -1) 0 else right - left + 1
    }

    @Test
    fun test() {
        0 assertEqualTo findUnsortedSubarray("[1,2,3,4]".toIntArray())
        3 assertEqualTo findUnsortedSubarray("[2,3,3,2,4]".toIntArray())
        5 assertEqualTo findUnsortedSubarray("[2,6,4,8,10,9,15]".toIntArray())
        0 assertEqualTo findUnsortedSubarray("[1]".toIntArray())
    }
}