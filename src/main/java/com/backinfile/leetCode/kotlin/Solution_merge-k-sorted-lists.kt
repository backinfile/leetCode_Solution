package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_merge-k-sorted-lists` {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) {
            return null
        }
        return mergeList(lists, 0, (lists.size - 1) / 2, lists.size - 1)
    }

    fun mergeList(lists: Array<ListNode?>, from: Int, middle: Int, to: Int): ListNode? {
        if (from == to) {
            return lists[from]
        }
        if (from + 1 == to) {
            return mergeNode(lists[from], lists[to])
        }
        return mergeNode(
            mergeList(lists, from, (middle + from) / 2, middle),
            mergeList(lists, middle + 1, (middle + to + 1) / 2, to)
        )
    }

    fun mergeNode(_a: ListNode?, _b: ListNode?): ListNode? {
        var a = _a
        var b = _b
        when {
            a == null -> return b
            b == null -> return a
        }

        var head: ListNode? = null
        if (a!!.`val` < b!!.`val`) {
            head = a;
            a = a.next
        } else {
            head = b
            b = b.next
        }

        var cur = head!!
        while (true) {
            if (a == null) {
                cur.next = b
                break
            }
            if (b == null) {
                cur.next = a
                break
            }
            if (a.`val` < b.`val`) {
                cur.next = a
                a = a.next
            } else {
                cur.next = b
                b = b.next
            }
            cur = cur.next!!
        }
        return head
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