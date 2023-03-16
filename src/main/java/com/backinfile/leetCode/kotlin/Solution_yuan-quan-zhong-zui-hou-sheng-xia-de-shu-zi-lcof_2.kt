package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof_2` {
    fun lastRemaining(n: Int, m: Int): Int {
        var result = 0
        for (i in 2..n) {
            result = (result + m) % i
        }
        return result
    }

    @Test
    fun test() {
        3 assertEqualTo lastRemaining(5, 3)
        2 assertEqualTo lastRemaining(10, 17)
    }
}