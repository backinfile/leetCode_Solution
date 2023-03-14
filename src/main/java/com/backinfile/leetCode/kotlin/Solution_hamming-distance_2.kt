package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_hamming-distance_2` {

    // 分治法
    fun hammingDistance(x: Int, y: Int): Int {
        var c = x xor y
        c = (c and 0x55555555) + ((c shr 1) and 0x55555555);
        c = (c and 0x33333333) + ((c shr 2) and 0x33333333);
        c = (c and 0x0f0f0f0f) + ((c shr 4) and 0x0f0f0f0f);
        c = (c and 0x00ff00ff) + ((c shr 8) and 0x00ff00ff);
        c = (c and 0x0000ffff) + ((c shr 16) and 0x0000ffff);
        return c
    }

    @Test
    fun test() {
        2 assertEqualTo hammingDistance(1, 4)
        1 assertEqualTo hammingDistance(1, 3)
    }
}