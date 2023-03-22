package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_qing-wa-tiao-tai-jie-wen-ti-lcof` {
    // 矩阵快速幂
    fun numWays(n: Int): Int {
        if (n <= 1) return 1
        return pow(arrayOf(intArrayOf(1, 1), intArrayOf(1, 0)), n)[0][0]
    }

    private fun pow(matrix: Array<IntArray>, n: Int): Array<IntArray> {
        var times = n
        var factor = matrix
        var result = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
        while (times > 0) {
            if (times and 1 != 0) {
                result = mul(result, factor)
            }
            times = times shr 1
            factor = mul(factor, factor)
        }
        return result
    }

    private fun mul(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
        val result = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
        for (i in result.indices) {
            for (j in result[0].indices) {
                result[i][j] = (result.indices.map { a[i][it] * 1L * b[it][j] }.sum() % 1000000007).toInt()
            }
        }
        return result
    }


    @Test
    fun test() {
        1 assertEqualTo numWays(0)
        2 assertEqualTo numWays(2)
        21 assertEqualTo numWays(7)
    }
}