package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toCharArrayArray
import org.junit.Test
import kotlin.contracts.contract

class `Solution_number-of-islands_3` {

    // 并查集实现
    fun numIslands(grid: Array<CharArray>): Int {
        val parent = IntArray(grid.size * grid[0].size) { -1 } // 初始化并查集 值-1表示父节点是自己

        // 初始化count为所有树的数目
        var count = grid.sumOf { it.count { ch -> ch == '1' } }

        // 查找节点的最顶端父节点
        fun findParent(coordinate: Int): Int {
            if (parent[coordinate] == -1) {
                return coordinate
            }
            parent[coordinate] = findParent(parent[coordinate]) // 压缩查询
            return parent[coordinate]
        }

        // 合并两个树，并count--
        fun unite(a: Int, b: Int) {
            val pa = findParent(a)
            val pb = findParent(b)
            if (pa == pb) {
                return
            }
            parent[pa] = pb
            count--
        }

        val factor = grid[0].size
        for (x in grid.indices) {
            for (y in grid[0].indices) {
                if (grid[x][y] != '1') {
                    continue
                }
                // 与相邻元素合并
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
        val DIR = arrayOf(1 to 0, 0 to 1) // 向右 向下即可
    }


    @Test
    fun test() {
        assert(3 == numIslands(Utils.readResource("input_number-of-islands_02.txt").toCharArrayArray()))
        assert(1 == numIslands(Utils.readResource("input_number-of-islands_01.txt").toCharArrayArray()))
        assert(1 == numIslands(Utils.readResource("input_number-of-islands_03.txt").toCharArrayArray()))
    }
}