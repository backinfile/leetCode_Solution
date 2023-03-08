package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toCharArrayArray
import org.junit.Test

class `Solution_number-of-islands_2` {

    // 广度优先搜索
    fun numIslands(grid: Array<CharArray>): Int {
        val visited = Array(grid.size) { BooleanArray(grid[0].size) }

        fun spead(fromX: Int, fromY: Int) {
            val queue = ArrayDeque<Pair<Int, Int>>()
            queue.addLast(fromX to fromY)
            while (!queue.isEmpty()) {
                val (x, y) = queue.removeFirst()
                if (visited[x][y]) {
                    continue
                }
                visited[x][y] = true
                for ((dx, dy) in DIR) {
                    val fx = x + dx
                    val fy = y + dy
                    if (fx !in grid.indices || fy !in grid[0].indices) {
                        continue
                    }
                    if (grid[fx][fy] == '0') {
                        continue
                    }
                    if (visited[fx][fy]) {
                        continue
                    }
                    queue.addLast(fx to fy)
                }
            }
        }

        var count = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '0') {
                    continue
                }
                if (visited[i][j]) {
                    continue
                }
                count++
                spead(i, j)
            }
        }

        println(count)
        return count
    }

    companion object {
        val DIR = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
    }


    @Test
    fun test() {
        assert(3 == numIslands(Utils.readResource("input_number-of-islands_02.txt").toCharArrayArray()))
        assert(1 == numIslands(Utils.readResource("input_number-of-islands_01.txt").toCharArrayArray()))
        assert(1 == numIslands(Utils.readResource("input_number-of-islands_03.txt").toCharArrayArray()))
    }
}