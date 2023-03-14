package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toCharArray
import org.junit.Test
import java.util.PriorityQueue

class `Solution_task-scheduler_2` {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        if (tasks.isEmpty()) {
            return 0
        }
        val queue = PriorityQueue<Int> { a, b -> b - a }
        tasks.groupBy { it }.forEach { (key, value) -> queue.add(value.size) }
        val part = n + 1

//        if (part >= queue.size) {
//            return (queue.first() - 1) * part + queue.count { it >= queue.first() }
//        } else {
//            return tasks.size
//        }
        return maxOf((queue.first() - 1) * part + queue.count { it >= queue.first() }, tasks.size)
    }

    @Test
    fun test() {
        16 assertEqualTo leastInterval(
            "[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"G\"]".toCharArray(),
            2
        )
        104 assertEqualTo leastInterval("[\"A\",\"A\",\"A\",\"B\",\"B\",\"B\"]".toCharArray(), 50)
        8 assertEqualTo leastInterval("[\"A\",\"A\",\"A\",\"B\",\"B\",\"B\"]".toCharArray(), 2)
        6 assertEqualTo leastInterval("[\"A\",\"A\",\"A\",\"B\",\"B\",\"B\"]".toCharArray(), 0)
    }
}