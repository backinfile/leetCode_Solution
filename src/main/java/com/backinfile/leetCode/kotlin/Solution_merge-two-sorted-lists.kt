package com.backinfile.leetCode.kotlin

class `Solution_merge-two-sorted-lists` {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val head = ListNode(-1)
        var tail = head

        var left = list1
        var right = list2
        while (left != null && right != null) {
            if (left.`val` < right.`val`) {
                tail.next = left
                tail = left
                left = left.next
            } else {
                tail.next = right
                tail = right
                right = right.next
            }
        }
        tail.next = left ?: right
        return head.next
    }
}