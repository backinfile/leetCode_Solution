package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArrayArray
import org.junit.Test
import java.util.ArrayDeque

class `Solution_course-schedule_2` {

    // 图 拓扑问题 广度优先
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        // 课能推导出的课
        val edgeMap = prerequisites.groupBy { it[1] }.map { it.key to it.value.map { l -> l[0] } }.toMap()

        // 入度 该节点还需求多少节课
        val indeg = prerequisites.groupBy { it[0] }.map { it.key to it.value.size }.toMap(HashMap())

        // 已经完成的课
        val queue = ArrayDeque((0 until numCourses).filter { 0 == (indeg[it] ?: 0) })
        var finishedCnt = queue.size

        while (queue.isNotEmpty()) {
            val number = queue.removeFirst()
            for (other in edgeMap[number] ?: listOf()) {
                val before = indeg[other]!!
                indeg[other] = before - 1 // 该课的需求科目-1，若正好减至0，表示这个课完成了，加入已完成队列
                if (before - 1 == 0) {
                    finishedCnt++
                    queue.add(other)
                }
            }
        }
        return finishedCnt == numCourses
    }

    @Test
    fun test() {
        assert(canFinish(2, "[[1,0]]".toIntArrayArray()))
        assert(!canFinish(2, "[[1,0],[0,1]]".toIntArrayArray()))
    }
}