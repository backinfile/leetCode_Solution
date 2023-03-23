package com.backinfile.leetCode.kotlin.sword

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_shu-zhi-de-zheng-shu-ci-fang-lcof` {
    fun myPow(x: Double, n: Int): Double {
        var factor = x
        var cur = 1.0
        var times = Math.abs(n.toLong())
        while (times > 0) {
            if (times and 1L != 0L) {
                cur *= factor
            }
            factor *= factor
            times = times shr 1
        }
        return if (n > 0) cur else 1 / cur
    }

    @Test
    fun test() {
        0.0 assertEqualTo myPow(2.0, -2147483648)
    }
}