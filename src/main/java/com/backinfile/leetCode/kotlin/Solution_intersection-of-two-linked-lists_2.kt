package com.backinfile.leetCode.kotlin

class `Solution_intersection-of-two-linked-lists_2` {
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        if (headA == null || headB == null) {
            return null
        }

        var lenA = getLength(headA)
        var lenB = getLength(headB)

        var curA = headA
        var curB = headB

        while (lenA != lenB) {
            if (lenA > lenB) {
                curA = curA!!.next
                lenA--
            } else {
                curB = curB!!.next
                lenB--
            }
        }

        while (curA != null && curB != null) {
            if (curA == curB) {
                return curA
            }
            curA = curA.next
            curB = curB.next
        }
        return null
    }

    private fun getLength(head: ListNode?): Int {
        var len = 0
        var cur: ListNode? = head
        while (cur != null) {
            cur = cur.next
            len++
        }
        return len
    }
}