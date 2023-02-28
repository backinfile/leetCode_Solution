package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_rotate-image` {
    fun rotate(matrix: Array<IntArray>): Unit {
        if (matrix.size <= 1) {
            return
        }

        val length = matrix.size
        for (i in 0..length / 2) {
            for (j in i until length - i - 1) {
                val x1 = j
                val y1 = length - i - 1

                val x2 = y1
                val y2 = length - x1 - 1

                val x3 = y2
                val y3 = length - x2 - 1

                val tmp = matrix[i][j]
                matrix[i][j] = matrix[x3][y3]
                matrix[x3][y3] = matrix[x2][y2]
                matrix[x2][y2] = matrix[x1][y1]
                matrix[x1][y1] = tmp
            }
        }
    }

    @Test
    fun test() {
        assert(testFunc("[[1,2,3],[4,5,6],[7,8,9]]", "[[7,4,1],[8,5,2],[9,6,3]]"))
        assert(
            testFunc(
                "[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]",
                "[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]"
            )
        )
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        val output = Utils.toIntArrayArray(inputStr)
        rotate(output)
        val answer = Utils.toIntArrayArray(answerStr).map { it.toList() }.toList()
        println(output.map { it.toList() }.toList())

        return output.map { it.toList() }.toList() == answer
    }
}