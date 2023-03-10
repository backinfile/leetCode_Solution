package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_counting-bits_3` {
    fun countBits(n: Int): IntArray {
        val result = IntArray(n + 1)
        for (i in 0..n) {
            result[i] = count(i)
        }
        return result
    }

    private fun count(n: Int): Int {
        var v = n
        var cnt = 0
        while (v > 0) {
            v = v and v - 1
            cnt++
        }
        return cnt
    }

    @Test
    fun test() {
        "[0,1,1]".toIntArray() assertEqualTo countBits(2)
        "[0,1,1,2,1,2]".toIntArray() assertEqualTo countBits(5)
    }
}