package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_counting-bits` {
    fun countBits(n: Int): IntArray {
        val result = IntArray(n + 1)


        for (i in 0 until 31) {
            val flag = 1 shl i
            for (v in 0..n) {
                if (v and flag != 0) {
                    result[v]++
                }
            }
        }
        return result
    }

    @Test
    fun test() {
        "[0,1,1]".toIntArray() assertEqualTo countBits(2)
        "[0,1,1,2,1,2]".toIntArray() assertEqualTo countBits(5)
    }
}