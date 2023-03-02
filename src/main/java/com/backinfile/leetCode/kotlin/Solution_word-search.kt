package com.backinfile.leetCode.kotlin

import com.backinfile.toCharArrayArray
import org.junit.Test

class `Solution_word-search` {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (board.isEmpty() || word == "") {
            return false
        }
        val width = board.size
        val height = board[0].size
        val widthRange = 0 until width
        val heightRange = 0 until height

        val visited = Array(width) { Array(height) { false } }

        fun isMatch(x: Int, y: Int, index: Int): Boolean {
            if (word[index] != board[x][y]) {
                return false
            }
            if (index + 1 == word.length) {
                return true
            }
            visited[x][y] = true
            for ((dx, dy) in DIR) {
                val fx = dx + x
                val fy = dy + y
                if (fx !in widthRange || fy !in heightRange) {
                    continue
                }
                if (visited[fx][fy]) {
                    continue
                }
                if (isMatch(fx, fy, index + 1)) {
                    return true
                }
            }
            visited[x][y] = false
            return false
        }

        for (i in widthRange) {
            for (j in heightRange) {
                if (isMatch(i, j, 0)) {
                    return true
                }
            }
        }
        return false
    }

    companion object {
        val DIR = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
    }


    @Test
    fun test() {
        assert(
            exist(
                "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"E\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]".toCharArrayArray(),
                "ABCESEEEFS"
            )
        )
        assert(
            exist(
                "[[\"a\"]]".toCharArrayArray(),
                "a"
            )
        )
        assert(
            exist(
                "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]".toCharArrayArray(),
                "ABCCED"
            )
        )
        assert(
            exist(
                "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]".toCharArrayArray(),
                "SEE"
            )
        )
        assert(
            !exist(
                "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]".toCharArrayArray(),
                "ABCB"
            )
        )
    }
}