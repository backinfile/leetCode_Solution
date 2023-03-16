package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof` {
    fun lastRemaining(n: Int, m: Int): Int {
        if (n == 1) {
            return 0
        }
        val last = lastRemaining(n - 1, m)
        return (last + m) % n
    }

    @Test
    fun test() {
        3 assertEqualTo lastRemaining(5, 3)
        2 assertEqualTo lastRemaining(10, 17)
    }
}