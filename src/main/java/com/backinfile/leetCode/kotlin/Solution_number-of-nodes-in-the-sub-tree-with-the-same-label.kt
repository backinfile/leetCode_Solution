package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArrayArray
import com.backinfile.toIntList
import org.junit.Test

class `Solution_number-of-nodes-in-the-sub-tree-with-the-same-label` {
    fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
        // 从下往上走
        val to = edges.groupBy { it[1] }.map { it.key to it.value.map { v -> v[0] } }.toMap()
        val from = edges.groupBy { it[0] }.map { it.key to it.value.map { v -> v[1] } }.toMap()

        val down = hashMapOf<Int, Set<Int>>()
        val up = hashMapOf<Int, Int>()

        val queue = java.util.ArrayDeque<Int>()
        queue.add(0)
        while (queue.isNotEmpty()) {
            val node = queue.pop()
            if (node in down) continue
            val nextNodes = hashSetOf<Int>()
            down[node] = nextNodes
            to[node]?.let { for (nextNode in it) if (nextNode !in down) nextNodes.add(nextNode) }
            from[node]?.let { for (nextNode in it) if (nextNode !in down) nextNodes.add(nextNode) }

            if (nextNodes.isNotEmpty()) {
                queue.addAll(nextNodes)
                nextNodes.forEach { up[it] = node }
            }
        }


        fun Int.label() = labels[this] - 'a'

        val dp = Array(n) { IntArray(26) }

        for (i in 0 until n) {
            var node = i
            val label = i.label()
            while (true) {
                dp[node][label]++
                node = up[node] ?: break
            }
        }

        return dp.mapIndexed { i, v -> v[i.label()] }.toIntArray()
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