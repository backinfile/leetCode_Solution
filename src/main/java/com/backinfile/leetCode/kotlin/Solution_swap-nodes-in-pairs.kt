package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toListNode
import org.junit.Test

class `Solution_swap-nodes-in-pairs` {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }
        if (head.next == null) {
            return head
        }

        val dummy = ListNode(-1).apply { next = head }
        var cur = dummy
        while (true) {
            if (cur.next?.next == null) {
                break
            }
            val next = cur.next!!.next!!.next
            val tail = cur.next!!
            cur.next = cur.next!!.next!!.also { it.next = cur.next }
            tail.next = next
            cur = tail
        }
        return dummy.next
    }

    @Test
    fun test() {
        "[2,1,4,3]".toListNode() assertEqualTo swapPairs("[1,2,3,4]".toListNode())
        "[1]".toListNode() assertEqualTo swapPairs("[1]".toListNode())
        "[]".toListNode() assertEqualTo swapPairs("[]".toListNode())
    }
}