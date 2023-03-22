package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Sulution_fei-bo-na-qi-shu-lie-lcof` {
    fun fib(n: Int): Int {
        when(n) {
            0 -> return 0
            1 -> return 1
        }
        var a = 0
        var b = 1
        for(i in 2..n) {
            b = ((a + b) % 1000000007).also { a = b }
        }
        return b
    }

    @Test
    fun test() {
        0 assertEqualTo fib(0)
        8 assertEqualTo fib(6)
    }
}