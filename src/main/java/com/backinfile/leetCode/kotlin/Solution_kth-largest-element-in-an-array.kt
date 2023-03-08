package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_kth-largest-element-in-an-array` {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val heap = Heap(k)
        nums.forEach { heap.add(it) }
        return heap.pop()
    }


    // 最小堆
    class Heap(private val capacity: Int) {
        val array = IntArray(capacity + 1)
        var size = 0
            private set

        fun add(num: Int) {
            // 超出数目上限时，弹出最小的，然后加入最新的
            if (size == array.size) {
                array[0] = num
                orderDown()
                return
            }

            array[size++] = num
            orderUp(size - 1)
        }

        fun pop(): Int {
            if (size == 1) {
                return array[--size]
            }
            if (size > capacity) {
                array[0] = array[--size]
                orderDown()
            }
            return array[0].also {
                array[0] = array[--size]
                orderDown()
            }
        }

        private fun orderUp(index: Int) {
            var cur = index
            while (cur != 0) {
                val parent = (cur - 1) / 2
                if (array[cur] < array[parent]) {
                    array.swap(cur, parent)
                }
                cur = parent
            }
        }

        private fun orderDown() {
            var cur = 0
            while (true) {
                val left = cur * 2 + 1
                val right = left + 1

                if (left >= size) {
                    break
                }
                val minChildIndex = when {
                    right < size -> if (array[left] < array[right]) left else right
                    else -> left
                }

                if (array[cur] < array[minChildIndex]) {
                    break
                }
                array.swap(cur, minChildIndex)
                cur = minChildIndex
            }
        }

        private fun IntArray.swap(a: Int, b: Int) {
            array[a] = array[b].also { array[b] = array[a] }
        }
    }

    @Test
    fun test() {
        assert(5 == findKthLargest("[3,2,1,5,6,4]".toIntArray(), 2))
        assert(4 == findKthLargest("[3,2,3,1,2,4,5,5,6]".toIntArray(), 4))
    }
}