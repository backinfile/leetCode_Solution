package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.assertSortedEqualTo
import com.backinfile.toIntArray
import org.junit.Test
import java.util.PriorityQueue

class `Solution_top-k-frequent-elements` {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val hashMap = hashMapOf<Int, Int>()
        nums.forEach { hashMap.compute(it) { _, oldValue -> (oldValue ?: 0) + 1 } }

        val queue = PriorityQueue<MutableMap.MutableEntry<Int, Int>> { a, b -> a.value.compareTo(b.value) }
        for (entry in hashMap.entries) {
            queue.add(entry)

            if (queue.size > k) {
                queue.poll()
            }
        }
        return queue.map { it.key }.toIntArray()
    }


    @Test
    fun test() {
        "[1,2]".toIntArray() assertSortedEqualTo topKFrequent("[1,1,1,2,2,3]".toIntArray(), 2)
        "[1]".toIntArray() assertSortedEqualTo topKFrequent("[1]".toIntArray(), 1)
    }
}