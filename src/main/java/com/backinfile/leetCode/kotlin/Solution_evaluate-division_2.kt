package com.backinfile.leetCode.kotlin

import com.backinfile.toDoubleArray
import com.backinfile.toStrListList
import org.junit.Test

class `Solution_evaluate-division_2` {
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val nodeIdMap = equations.asSequence().flatten().distinct().mapIndexed { index, s -> s to index }.toMap()

        val parent = IntArray(nodeIdMap.size) { -1 }
        val weight = DoubleArray(nodeIdMap.size) { 1.0 }

        fun findParent(a: Int): Pair<Int, Double> {
            if (parent[a] == -1) {
                return a to 1.0
            }
            val (p, w) = findParent(parent[a])
            parent[a] = p
            weight[a] = w * weight[a]
            return parent[a] to weight[a]
        }

        for ((index, nodes) in equations.withIndex()) {
            val fromId = nodeIdMap[nodes[0]]!!
            val toId = nodeIdMap[nodes[1]]!!


            val (pA, wA) = findParent(fromId)
            val (pB, wB) = findParent(toId)

            if (pA == pB) {
                continue
            }

            parent[pA] = pB
            weight[pA] = values[index] * wB / wA
        }

        val result = DoubleArray(queries.size) { -1.0 }

        for ((index, nodes) in queries.withIndex()) {
            val fromId = nodeIdMap[nodes[0]]
            val toId = nodeIdMap[nodes[1]]
            if (fromId == null || toId == null) {
                continue
            }
            val (pA, wA) = findParent(fromId)
            val (pB, wB) = findParent(toId)
            if (pA != pB) {
                continue
            }
            result[index] = wA / wB
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