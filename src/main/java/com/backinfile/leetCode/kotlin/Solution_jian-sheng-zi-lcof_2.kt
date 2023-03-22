package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test
import kotlin.math.max

class `Solution_jian-sheng-zi-lcof_2` {
    fun cuttingRope(n: Int): Int {
        if (n == 2) return 1
        if (n == 3) return 2
        val v = n / 3
        val left = n - v * 3
        return maxOf(
            Math.pow(3.0, v.toDouble()).toInt() * maxOf(left, cuttingWith2(left)),
            Math.pow(3.0, v.toDouble() - 1).toInt() * maxOf(left + 3, cuttingWith2(left + 3))
        )
    }

    private fun cuttingWith2(n: Int): Int {
        val cnt = n / 2
        return maxOf(Math.pow(2.0, cnt.toDouble()).toInt(), n)
    }

    @Test
    fun test() {
        324 assertEqualTo cuttingRope(16)
        9 assertEqualTo cuttingRope(6)
        2 assertEqualTo cuttingRope(3)
        4 assertEqualTo cuttingRope(4)
        36 assertEqualTo cuttingRope(10)
        54 assertEqualTo cuttingRope(11)
        324 assertEqualTo cuttingRope(16)
        486 assertEqualTo cuttingRope(17)
    }
}