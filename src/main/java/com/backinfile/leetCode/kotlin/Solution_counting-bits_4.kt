package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_counting-bits_4` {
    fun countBits(n: Int): IntArray {
        val result = IntArray(n + 1)
        for (i in 1..n) {
            result[i] = result[i and i - 1] + 1
        }
        return result
    }

    @Test
    fun test() {
        "[0,1,1]".toIntArray() assertEqualTo countBits(2)
        "[0,1,1,2,1,2]".toIntArray() assertEqualTo countBits(5)
    }
}