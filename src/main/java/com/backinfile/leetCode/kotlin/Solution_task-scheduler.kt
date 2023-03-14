package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toCharArray
import org.junit.Test
import java.util.PriorityQueue

class `Solution_task-scheduler` {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val queue = PriorityQueue<CharCount>() { a, b -> b.count - a.count }
        tasks.groupBy { it }.forEach { (key, value) -> queue.add(key to value.size) }

        var total = tasks.size
        val part = n + 1
        var result = 0


        while (total > 0) {
            val pops = arrayListOf<CharCount>()
            for (i in 0 until part) {
                if (queue.isNotEmpty()) {
                    pops.add(queue.poll())
                } else {
                    break
                }
            }

            for (cc in pops) {
                total--
                cc.count--
                if (cc.count > 0) {
                    queue.add(cc)
                }
            }

            result += if (queue.isNotEmpty()) part else pops.size
        }
        return result
    }

    data class CharCount(val char: Char, var count: Int)

    infix fun Char.to(count: Int): CharCount {
        return CharCount(this, count)
    }

    @Test
    fun test() {
        8 assertEqualTo leastInterval("[\"A\",\"A\",\"A\",\"B\",\"B\",\"B\"]".toCharArray(), 2)
        6 assertEqualTo leastInterval("[\"A\",\"A\",\"A\",\"B\",\"B\",\"B\"]".toCharArray(), 0)
        16 assertEqualTo leastInterval(
            "[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"G\"]".toCharArray(),
            2
        )
    }
}