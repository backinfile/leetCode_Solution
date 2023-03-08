package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toCharArrayArray
import org.junit.Test
import kotlin.contracts.contract

class `Solution_number-of-islands_3` {

    // 并查集实现
    fun numIslands(grid: Array<CharArray>): Int {
        val factor = grid[0].size
        val parent = IntArray(grid.size * grid[0].size) { -1 }

        var count = grid.sumOf { it.count { ch -> ch == '1' } }

        fun findParent(coordinate: Int): Int {
            if (parent[coordinate] == -1) {
                return coordinate
            }
            parent[coordinate] = findParent(parent[coordinate])
            return parent[coordinate]
        }

        fun unite(a: Int, b: Int) {
            val pa = findParent(a)
            val pb = findParent(b)
            if (pa == pb) {
                return
            }
            parent[pa] = pb
            count--
        }

        for (x in grid.indices) {
            for (y in grid[0].indices) {
                if (grid[x][y] != '1') {
                    continue
                }
                for ((dx, dy) in DIR) {
                    val fx = x + dx
                    val fy = y + dy
                    if (fx !in grid.indices || fy !in grid[0].indices) {
                        continue
                    }
                    if (grid[fx][fy] == '0') {
                        continue
                    }
                    unite(x * factor + y, fx * factor + fy)
                }
            }
        }

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