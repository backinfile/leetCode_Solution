package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArrayArray
import org.junit.Test

class `Solution_course-schedule` {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val preMap = prerequisites.groupBy { it[0] }.map { it.key to it.value.map { list -> list[1] } }.toMap(HashMap())
        val unfinished = preMap.keys.toMutableSet()
        unfinished@ while (unfinished.isNotEmpty()) {
            for (number in unfinished) {
                val pres = preMap[number]
                if (pres != null && pres.any { it in unfinished }) {
                    continue
                }
                unfinished.remove(number)
                continue@unfinished
            }
            return false
        }
        return true
    }

    @Test
    fun test() {
        assert(canFinish(2, "[[1,0]]".toIntArrayArray()))
        assert(!canFinish(2, "[[1,0],[0,1]]".toIntArrayArray()))
    }
}