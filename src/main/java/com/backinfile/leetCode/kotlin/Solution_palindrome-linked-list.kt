package com.backinfile.leetCode.kotlin

class `Solution_palindrome-linked-list` {
    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null) {
            return true
        }
        val list = head.toList()
        var left = 0
        var right = list.lastIndex

        while (left < right) {
            if (list[left++] != list[right--]) {
                return false
            }
        }
        return true
    }

    private fun ListNode?.toList(): ArrayList<Int> {
        val list = ArrayList<Int>()
        var cur = this
        while (cur != null) {
            list.add(cur.`val`)
            cur = cur.next
        }
        return list
    }
}