package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_climbing-stairs` {
    fun climbStairs(n: Int): Int {
        if (n <= 2) {
            return n
        }
        var before = 1
        var cur = 2
        for (i in 3..n) {
            cur += before.also { before = cur }
        }
        return cur
    }

    @Test
    fun test() {
        assert(2 == climbStairs(2))
        assert(3 == climbStairs(3))
        assert(5 == climbStairs(4))
        assert(8 == climbStairs(5))
    }
}