package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test
import java.security.DrbgParameters.Capability
import kotlin.math.min

class `Solution_merge-k-sorted-lists_3` {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val heap = Heap(lists.size)
        lists.forEach { it?.let { heap.add(it) } }

        if (heap.size == 0) {
            return null
        }

        val head = heap.pollMin()
        var cur = head
        while (heap.size > 0) {
            val node = heap.pollMin()
            cur.next = node
            cur = cur.next!!
        }
        return head
    }

    class Heap(capability: Int) {
        private val lists = Array<ListNode?>(capability) { null }
        var size = 0
        fun pollMin(): ListNode {
            val min = lists[0]
            if (min!!.next != null) {
                lists[0] = min.next!!
                down()
            } else {
                size--
                lists[0] = lists[size]
                down()
            }
            return min
        }

        fun add(node: ListNode) {
            lists[size] = node
            up(size)
            size++
        }

        private fun down() {
            var cur = 0

            while (true) {
                val left = cur * 2 + 1
                val right = left + 1
                val minIndex = when {
                    right < size -> if (lists[left]!!.`val` < lists[right]!!.`val`) left else right
                    left < size -> left
                    else -> return
                }
                if (lists[cur]!!.`val` < lists[minIndex]!!.`val`) {
                    return
                }

                val tmp = lists[cur]
                lists[cur] = lists[minIndex]
                lists[minIndex] = tmp
                cur = minIndex
            }

        }

        private fun up(index: Int) {
            var cur = index
            while (cur > 0) {
                val parent = (cur - 1) / 2
                if (lists[parent]!!.`val` < lists[cur]!!.`val`) {
                    return
                }
                val tmp = lists[parent]
                lists[parent] = lists[cur]
                lists[cur] = tmp
                cur = parent
            }
        }
    }

    @Test
    fun testHeap() {
        val heap = Heap(10)
        heap.add(ListNode(1))
        heap.add(ListNode(2))
        assert(heap.pollMin().`val` == 1)
        assert(heap.pollMin().`val` == 2)
    }

    @Test
    fun test() {
        assert(testFunc("[[1,4,5],[1,3,4],[2,6]]", "[1,1,2,3,4,4,5,6]"));
        assert(testFunc("[]", "[]"));
        assert(testFunc("[[]]", "[]"));
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        val input = Utils.toIntIntArray(inputStr).map(::toListNode).toTypedArray()
        val answer = toListNode(answerStr)
        val output = mergeKLists(input)
        return listNodeEquals(output, answer)
    }
}