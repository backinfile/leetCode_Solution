package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toListNode
import org.junit.Test

class `Solution_swap-nodes-in-pairs_2` {
    fun swapPairs(head: ListNode?): ListNode? {
        val cur = head ?: return null
        val next = head.next ?: return head
        val nextNext = next.next

        next.next = cur
        cur.next = swapPairs(nextNext)
        return next
    }

    @Test
    fun test() {
        "[2,1,4,3]".toListNode() assertEqualTo swapPairs("[1,2,3,4]".toListNode())
        "[1]".toListNode() assertEqualTo swapPairs("[1]".toListNode())
        "[]".toListNode() assertEqualTo swapPairs("[]".toListNode())
    }
}