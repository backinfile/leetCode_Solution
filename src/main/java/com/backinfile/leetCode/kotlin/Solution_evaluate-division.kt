package com.backinfile.leetCode.kotlin

import com.backinfile.toDoubleArray
import com.backinfile.toStrList
import com.backinfile.toStrListList
import org.junit.Test

class `Solution_evaluate-division` {
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val nodeIdMap = equations.asSequence().flatten().distinct().mapIndexed { index, s -> s to index }.toMap()

        val len = nodeIdMap.size
        val map = Array(len) { DoubleArray(len) }
        for ((index, nodes) in equations.withIndex()) {
            val fromId = nodeIdMap[nodes[0]]!!
            val toId = nodeIdMap[nodes[1]]!!
            map[fromId][toId] = values[index]
            map[toId][fromId] = 1 / values[index]
        }

        val visited = BooleanArray(len)
        val queue = java.util.ArrayDeque<Pair<Int, Double>>(len)

        val result = DoubleArray(queries.size) { -1.0 }
        for ((index, nodes) in queries.withIndex()) {
            val fromId = nodeIdMap[nodes[0]]
            val toId = nodeIdMap[nodes[1]]
            if (fromId == null || toId == null) {
                continue
            }

            queue.clear()
            visited.fill(false)

            queue.add(fromId to 1.0)

            while (queue.isNotEmpty()) {
                val (nodeId, value) = queue.pop()
                if (nodeId == toId) {
                    result[index] = value
                    break
                }
                visited[nodeId] = true
                for (i in map.indices) {
                    if (visited[i]) continue
                    if (map[nodeId][i] != 0.0) {
                        queue.add(i to value * map[nodeId][i])
                    }
                }
            }
            if (result[index] == 0.0) {
                result[index] = -1.0;
            }
        }
        return result
    }

    @Test
    fun test() {
        assert(
            testFunc(
                "[[\"a\",\"b\"]]",
                "[0.5]",
                "[[\"a\",\"b\"],[\"b\",\"a\"],[\"a\",\"c\"],[\"x\",\"y\"]]",
                "[0.50000,2.00000,-1.00000,-1.00000]"
            )
        )
        assert(
            testFunc(
                "[[\"a\",\"b\"],[\"b\",\"c\"]]",
                "[2.0,3.0]",
                "[[\"a\",\"c\"],[\"b\",\"a\"],[\"a\",\"e\"],[\"a\",\"a\"],[\"x\",\"x\"]]",
                "[6.00000,0.50000,-1.00000,1.00000,-1.00000]"
            )
        )
        assert(
            testFunc(
                "[[\"a\",\"b\"],[\"b\",\"c\"],[\"bc\",\"cd\"]]",
                "[1.5,2.5,5.0]",
                "[[\"a\",\"c\"],[\"c\",\"b\"],[\"bc\",\"cd\"],[\"cd\",\"bc\"]]",
                "[3.75000,0.40000,5.00000,0.20000]"
            )
        )
    }

    private fun testFunc(equations: String, values: String, queries: String, answerStr: String): Boolean {
        val output = calcEquation(equations.toStrListList(), values.toDoubleArray(), queries.toStrListList()).toList()
        val answer = answerStr.toDoubleArray().toList()
        return (output == answer).also { if (!it) println("answer:$answer\noutput:$output") }
    }
}