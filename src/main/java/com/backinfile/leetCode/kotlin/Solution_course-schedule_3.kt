package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArrayArray
import org.junit.Test
import java.util.ArrayDeque

class `Solution_course-schedule_3` {

    // 图 拓扑问题 深度优先 递归
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        // 入度 该节点需求的课
        val indeg = prerequisites.groupBy { it[0] }.map { it.key to it.value.map { l -> l[1] } }.toMap(HashMap())

        val visited = BooleanArray(numCourses) { false } // 全局visited，表示这个节点是否处理过
        val visitedInPath = BooleanArray(numCourses) { false } // 在同一次查找过程中的visited

        // 返回false表示有环
        fun spread(num: Int): Boolean {
            if (visitedInPath[num]) { // 如果在同一次的查找中找到了之前走过的路径，说明是环
                return false
            }
            if (visited[num]) {
                return true
            }
            visited[num] = true
            visitedInPath[num] = true

            indeg[num]?.forEach { if (!spread(it)) return false } // 如果有环，中途退出即可

            visitedInPath[num] = false
            return true
        }

        // 所有课全过一遍，没环即可
        return (0 until numCourses).all { spread(it) }
    }

    @Test
    fun test() {
        assert(!canFinish(2, "[[1,0],[0,1]]".toIntArrayArray()))
        assert(canFinish(2, "[[1,0]]".toIntArrayArray()))
    }
}