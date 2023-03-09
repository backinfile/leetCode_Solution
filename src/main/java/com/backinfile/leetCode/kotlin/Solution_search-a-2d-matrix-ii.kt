package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArrayArray
import org.junit.Test

class `Solution_search-a-2d-matrix-ii` {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val width = matrix.lastIndex
        val height = matrix[0].lastIndex

        fun search(fromX: Int, toX: Int, fromY: Int, toY: Int): Boolean {
            if (fromX == toX) return biSearch(fromY, toY, target) { matrix[fromX][it] }
            if (fromY == toY) return biSearch(fromX, toX, target) { matrix[it][fromY] }

            if (target < matrix[fromX][fromY] || target > matrix[toX][toY]) {
                return false
            }

            val midX = (fromX + toX) / 2
            val midY = (fromY + toY) / 2

            return search(fromX, midX, fromY, toY) || search(midX + 1, toX, fromY, toY)
                    || search(fromX, toX, fromY, midY) || search(fromX, toX, midY + 1, toY)
        }
        return search(0, width, 0, height)
    }

    // 查找第一个>=target的位置
    private fun biSearch(from: Int, to: Int, target: Int, key: (Int) -> Int): Boolean {
        var left = from
        var right = to
        while (left <= right) {
            val mid = (right - left) / 2 + left
            val value = key(mid)
            if (value > target) {
                right = mid - 1
            } else if (value < target) {
                left = mid + 1
            } else {
                return true
            }
        }
        return false
    }


    @Test
    fun test() {
        assert(
            searchMatrix(
                "[[3,3,8,13,13,18],[4,5,11,13,18,20],[9,9,14,15,23,23],[13,18,22,22,25,27],[18,22,23,28,30,33],[21,25,28,30,35,35],[24,25,33,36,37,40]]".toIntArrayArray(),
                21
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
                "[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]".toIntArrayArray(),
                15
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
    }
}