package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_hamming-distance` {
    fun hammingDistance(x: Int, y: Int): Int {
        var result = x xor y
        var cnt = 0
        while (result > 0) {
            result = result and result - 1
            cnt++
        }
        return cnt
    }

    @Test
    fun test() {
        2 assertEqualTo hammingDistance(1, 4)
        1 assertEqualTo hammingDistance(1, 3)
    }
}