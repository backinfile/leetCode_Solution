package com.backinfile.leetCode.kotlin

class `Solution_ju-zhen-zhong-de-lu-jing-lcof` {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val visited = Array (board.size) {BooleanArray(board[0].size) {false}}
        fun check(index:Int, x:Int, y:Int):Boolean {
            println("check $x,$y,$index")
            if (index + 1 == word.length) return true
            if (visited[x][y]) return false
            visited[x][y] = true
            for((dx, dy) in DIR) {
                val fx = x + dx
                val fy = y + dy
                println("next $fx,$fy,$index,${word[index + 1]}")
                if (fx !in board.indices || fy !in board[0].indices) continue
                if (board[fx][fy] != word[index + 1]) continue
                println("next $fx,$fy,$index")
                if (check(index + 1, fx, fy)) return true
            }
            visited[x][y] = false
            return false
        }

        for (i in board.indices) {
            for(j in board[0].indices) {
                if (word[0] != board[i][j]) continue
                visited.forEach {it.fill(false)}
                if (check(0, i, j)) {
                    return true
                }
            }
        }
        return false;
    }
    fun cuttingRope(n: Int): Int {
        val v = Math.floor(Math.sqrt(n.toDouble())).toInt()
        val pow = Math.pow(v.toDouble(), (v - 1).toDouble()).toInt()
        return pow * (n - v * (v - 1))
    }
    companion object {
        val DIR = mapOf(1 to 0, -1 to 0, 0 to -1, 0 to 1)
    }
}