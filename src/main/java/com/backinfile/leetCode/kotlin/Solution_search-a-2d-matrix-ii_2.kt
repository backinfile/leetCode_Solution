package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArrayArray
import org.junit.Test

class `Solution_search-a-2d-matrix-ii_2` {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var x = matrix[0].size - 1
        var y = 0
        while (x >= 0 && y < matrix.size) {
            when {
                matrix[y][x] > target -> x--
                matrix[y][x] < target -> y++
                else -> return true
            }
        }
        return false
    }


    @Test
    fun test() {
        assert(
            searchMatrix(
                "[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]".toIntArrayArray(),
                15
            )
        )
        assert(
            !searchMatrix(
                "[[-5]]".toIntArrayArray(),
                -2
            )
        )
        assert(
            searchMatrix(
                "[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]".toIntArrayArray(),
                5
            )
        )
        assert(
            !searchMatrix(
                "[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]".toIntArrayArray(),
                20
            )
        )
        assert(
            searchMatrix(
                "[[3,3,8,13,13,18],[4,5,11,13,18,20],[9,9,14,15,23,23],[13,18,22,22,25,27],[18,22,23,28,30,33],[21,25,28,30,35,35],[24,25,33,36,37,40]]".toIntArrayArray(),
                21
            )
        )
    }
}