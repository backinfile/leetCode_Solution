package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArrayArray
import com.backinfile.toIntList
import org.junit.Test

class `Solution_number-of-nodes-in-the-sub-tree-with-the-same-label_TLE` {
    fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
        val nextMap = hashMapOf<Int, HashSet<Int>>()
        for ((from, to) in edges) {
            nextMap.computeIfAbsent(from) { HashSet() }.add(to)
            nextMap.computeIfAbsent(to) { HashSet() }.add(from)
        }


        fun Int.label() = labels[this] - 'a'

        val result = IntArray(n)
        fun dfs(i: Int, from: Int = -1): IntArray {
            var counts: IntArray? = null
            nextMap[i]?.let {
                for (next in it) {
                    if (next == from) {
                        continue
                    }
                    counts = counts union dfs(next, i)
                }
            }
            val countsResult = counts ?: IntArray(26)
            countsResult[i.label()]++
            result[i] = countsResult[i.label()]
            return countsResult
        }
        dfs(0)
        return result
    }

    private infix fun IntArray?.union(array: IntArray): IntArray {
        if (this == null) return array
        for (i in this.indices) {
            this[i] += array[i]
        }
        return this
    }

    @Test
    fun test() {
        "[1,1,2,1]".toIntList() assertEqualTo countSubTrees(
            4,
            "[[0,2],[0,3],[1,2]]".toIntArrayArray(),
            "aeed"
        ).toList()

        "[2,1,1,1,1,1,1]".toIntList() assertEqualTo countSubTrees(
            7,
            "[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]".toIntArrayArray(),
            "abaedcd"
        ).toList()

        "[4,2,1,1]".toIntList() assertEqualTo countSubTrees(
            4,
            "[[0,1],[1,2],[0,3]]".toIntArrayArray(),
            "bbbb"
        ).toList()

        "[3,2,1,1,1]".toIntList() assertEqualTo countSubTrees(
            5,
            "[[0,1],[0,2],[1,3],[0,4]]".toIntArrayArray(),
            "aabab"
        ).toList()
    }
}