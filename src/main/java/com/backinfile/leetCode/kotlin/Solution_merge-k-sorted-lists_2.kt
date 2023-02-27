package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_merge-k-sorted-lists_2` {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val head = ListNode(-1)
        var cur = head

        while (true) {
            var minIndex = -1
            var minValue = 0

            lists.forEachIndexed { index, node ->
                if (node != null) {
                    if (minIndex == -1 || node.`val` < minValue) {
                        minIndex = index
                        minValue = node.`val`
                    }
                }
            }

            if (minIndex == -1) {
                break
            }

            cur.next = lists[minIndex]
            cur = cur.next!!
            lists[minIndex] = cur.next
        }
        return head.next
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