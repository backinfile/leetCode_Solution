package com.backinfile.leetCode.kotlin

class `Solution_intersection-of-two-linked-lists` {
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        val hashSet = hashSetOf<ListNode>()
        var cur = headA
        while (cur != null) {
            hashSet.add(cur)
            cur = cur.next
        }
        cur = headB
        while (cur != null) {
            if (cur in hashSet) {
                return cur
            }
            hashSet.add(cur)
        }
        return null
    }
}