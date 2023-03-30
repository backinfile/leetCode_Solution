package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_jump-game-vi` {

    fun maxResult(nums: IntArray, k: Int): Int {

        val dp = IntArray(nums.size)
        val heap = Heap(nums.size) { dp[it] } // 改为记录数组index

        for (i in nums.indices) {
            if (i == 0) {
                dp[i] = nums[i]
                heap.add(i)
                continue
            }
            dp[i] = nums[i] + dp[heap.top()]

            heap.add(i)
            if (heap.top() <= i - k) {
                while (heap.top() <= i - k) { // 在取最大值前，把超出区域的去掉
                    heap.pop()
                }
            }
        }
        return dp[nums.lastIndex]
    }

    // 最大堆
    private class Heap(capacity: Int, val key: (Int) -> Int) {
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
                val childIndex = if (right < size && key(array[right]) > key(array[left])) right else left
                if (key(array[cur]) >= key(array[childIndex])) {
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
                if (key(array[cur]) <= key(array[parent])) {
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
        0 assertEqualTo maxResult(" [1,-5,-20,4,-1,3,-6,-3]".toIntArray(), 2)
        7 assertEqualTo maxResult("[1,-1,-2,4,-7,3]".toIntArray(), 2)
        17 assertEqualTo maxResult("[10,-5,-2,4,0,3]".toIntArray(), 3)
    }
}