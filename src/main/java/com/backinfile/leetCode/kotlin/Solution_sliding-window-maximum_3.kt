package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import com.backinfile.toIntList
import org.junit.Test

class `Solution_sliding-window-maximum_3` {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val heap = Heap(nums.size) { a, b -> nums[a] - nums[b] } // 改为记录数组index
        for (i in 0 until k - 1) {
            heap.add(i)
        }

        val result = IntArray(nums.size - k + 1)

        for (i in k - 1 until nums.size) {
            heap.add(i)
            while (heap.top() <= i - k) { // 在取最大值前，把超出区域的去掉
                heap.pop()
            }
            result[i - k + 1] = nums[heap.top()]
        }
        return result
    }

    // 最大堆
    private class Heap(capacity: Int, val cmp: (Int, Int) -> Int) {
        private val array = IntArray(capacity)
        private var size = 0

        fun top(): Int {
            return array[0]
        }

        fun add(value: Int) {
            array[size++] = value
            siftUp(size - 1)
        }

        fun pop(): Int {
            if (size <= 1) {
                return array[--size]
            }
            return array[0].also {
                swap(0, --size)
                siftDown()
            }
        }

        private fun siftDown() {
            var cur = 0
            while (true) {
                val left = cur * 2 + 1
                val right = left + 1

                if (left >= size) {
                    break
                }
                // 最大堆，取更大的值向上移动
                val childIndex = if (right < size && array[right] largeThen array[left]) right else left
                if (array[cur] largeEqualThen array[childIndex]) {
                    break
                }
                swap(cur, childIndex)
                cur = childIndex
            }
        }

        private fun siftUp(index: Int) {
            var cur = index
            while (cur > 0) {
                val parent = (cur - 1) / 2
                if (array[cur] lessEqualThen array[parent]) {
                    break
                }
                swap(cur, parent)
                cur = parent
            }
        }

        private fun swap(a: Int, b: Int) {
            if (a != b) {
                array[a] = array[b].also { array[b] = array[a] }
            }
        }

        private infix fun Int.largeThen(b: Int): Boolean {
            return cmp(this, b) > 0
        }

        private infix fun Int.largeEqualThen(b: Int): Boolean {
            return cmp(this, b) >= 0
        }

        private infix fun Int.lessEqualThen(b: Int): Boolean {
            return cmp(this, b) <= 0
        }

    }

    @Test
    fun test() {
        "[3,3,5,5,6,7]".toIntList() assertEqualTo maxSlidingWindow("[1,3,-1,-3,5,3,6,7]".toIntArray(), 3).toList()
        "[1]".toIntList() assertEqualTo maxSlidingWindow("[1]".toIntArray(), 1).toList()
    }
}