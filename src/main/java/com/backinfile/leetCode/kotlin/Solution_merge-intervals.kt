package com.backinfile.leetCode.kotlin

import com.backinfile.sorted
import com.backinfile.toIntArrayArray
import com.backinfile.toIntListList
import org.junit.Test
import kotlin.math.round

class `Solution_merge-intervals` {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.isEmpty()) {
            return listOf<IntArray>().toTypedArray()
        }

        val result = ArrayList<IntArray>()
//        intervals.sortBy { it[0] }
        fastSort(intervals, { it[0] }, 0, intervals.lastIndex)

        var (lastLeft, lastRight) = intervals[0]
        for ((left, right) in intervals) {
            if (left > lastRight) {
                result.add(intArrayOf(lastLeft, lastRight))
                lastLeft = left
                lastRight = right
            } else {
                lastRight = Math.max(lastRight, right)
            }
        }
        result.add(intArrayOf(lastLeft, lastRight))
        return result.toTypedArray()
    }

    private fun <T> Array<T>.swap(a: Int, b: Int) =
        if (a != b) this[a] = this[b].also { this[b] = this[a] }
        else Unit


    private fun <T> fastSort(array: Array<T>, key: (T) -> Int, from: Int, to: Int) {
        if (from >= to) {
            return
        }

        val target = key(array[to])
        var left = from
        for (i in from until to) {
            if (key(array[i]) < target) {
                array.swap(i, left)
                left++
            }
        }
        val mid = left
        array.swap(mid, to)
//        println("in sort from=$from to:$to target:$target ${array.toList()} l:$left")
        fastSort(array, key, from, mid - 1)
        fastSort(array, key, mid + 1, to)
    }


    @Test
    fun testSort() {
//        assert(testSortFunc(arrayOf(1, 2, 3)))
        assert(testSortFunc(arrayOf(3, 2, 1)))
        assert(testSortFunc(arrayOf(5, 324, 32, 5, 32, 23, 234, 12, 0)))
        assert(testSortFunc(arrayOf(3, 2, 1, 0, -1, -2, -3)))
        assert(testSortFunc(arrayOf()))
        assert(testSortFunc(arrayOf()))
    }

    private fun testSortFunc(array: Array<Int>): Boolean {
        val input = array.sorted()
        val output = array.let { fastSort(it, { a -> a }, 0, array.lastIndex); it.toList() }
        println(output)
        return input == output
    }

    @Test
    fun test() {
        assert(testFunc("[[1,3],[2,6],[8,10],[15,18]]", "[[1,6],[8,10],[15,18]]"))
        assert(testFunc("[[1,4],[4,5]]", "[[1,5]]"))
        assert(testFunc("[[1,4],[2,3]]", "[[1,4]]"))
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        val output = merge(inputStr.toIntArrayArray()).toIntListList().sorted()
        val answer = answerStr.toIntListList().sorted()
        return output == answer
    }
}