package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import com.backinfile.toIntList
import org.junit.Test

class `Solution_sliding-window-maximum` {
    // 优先队列
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val heap = Heap(k)
        for (i in 0 until k) {
            heap.add(nums[i])
        }

        val result = IntArray(nums.size - k + 1)

        result[0] = heap.getMax()
        for (i in k until nums.size) {
            heap.remove(nums[i - k])
            heap.add(nums[i])
            result[i - k + 1] = heap.getMax()
        }
        return result
    }

    // 最大堆
    private class Heap(capacity: Int) {
        private val array = IntArray(capacity)
        private var size = 0

        fun getMax(): Int {
            return array[0]
        }

        fun add(value: Int) {
            array[size++] = value
            siftUp(size - 1)
        }

        fun remove(value: Int) {
            val index = array.indexOf(value)
            if (index == size - 1) {
                size--
                return
            }
            swap(index, --size)

            if (index == 0) {
                siftDown(0)
                return
            }

            if (array[index] > array[(index - 1) / 2]) {
                siftUp(index)
            } else {
                siftDown(index)
            }
        }

        private fun siftDown(index: Int) {
            var cur = index
            while (true) {
                val left = cur * 2 + 1
                val right = left + 1

                if (left >= size) {
                    break
                }
                // 最大堆，取更大的值向上移动
                val childIndex = if (right < size && array[right] > array[left]) right else left
                if (array[cur] >= array[childIndex]) {
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
                if (array[cur] <= array[parent]) {
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

    }

    @Test
    fun test() {
        "[3,3,5,5,6,7]".toIntList() assertEqualTo maxSlidingWindow("[1,3,-1,-3,5,3,6,7]".toIntArray(), 3).toList()
        "[1]".toIntList() assertEqualTo maxSlidingWindow("[1]".toIntArray(), 1).toList()
    }
}