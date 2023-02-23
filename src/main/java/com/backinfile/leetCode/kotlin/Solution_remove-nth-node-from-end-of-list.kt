package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_remove-nth-node-from-end-of-list` {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) {
            return null
        }
        var cur = head
        for (i in 1 .. n) {
            if (cur != null) {
                cur = cur.next
            } else if (1 == n) { // 删除list中第一个
                return head.next
            } else {
                return head
            }
        }

        if (cur == null) {
            return head.next
        }

        var delHead = head
        while (cur!!.next != null) {
            cur = cur.next
            delHead = delHead!!.next
        }

        delHead!!.next = delHead.next!!.next
        return head
    }

    @Test
    fun test() {
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2,3,4,5]"), 2), toListNode("[1,2,3,5]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1]"), 1), toListNode("[]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2]"), 1), toListNode("[1]")))

    }

    @Test
    fun test2() {
        assert(listNodeEquals(removeNthFromEnd(toListNode("[]"), 1), toListNode("[]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2,3,4,5]"), 1), toListNode("[1,2,3,4]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2,3,4,5]"), 5), toListNode("[2,3,4,5]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2,3,4,5]"), 6), toListNode("[1,2,3,4,5]")))
    }
}