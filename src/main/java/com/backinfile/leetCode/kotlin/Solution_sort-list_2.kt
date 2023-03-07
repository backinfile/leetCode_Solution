package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toIntArray
import com.backinfile.toListNode
import org.junit.Test

class `Solution_sort-list_2` {

    // 自顶到下分治法
    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }

        var slow:ListNode? = head
        var fast:ListNode? = head.next
        while (fast?.next != null) {
            slow = slow!!.next
            fast = fast.next?.next
        }

        var right = sortList(slow?.next?.also { slow.next = null })
        var left = sortList(head)

        val dummy = ListNode(-1)
        var tail = dummy
        while (right != null && left != null) {
            if (right.`val` < left.`val`) {
                tail.next = right
                tail = right
                right = right.next
            } else {
                tail.next = left
                tail = left
                left = left.next
            }
        }
        tail.next = right ?: left
        return dummy.next
    }


    @Test
    fun test() {
        assert(testFunc("[4,2,1,3]"))
        assert(testFunc("[-1,5,3,4,0]"))
        assert(testFunc("[]"))
    }

    @Test
    fun test2() {
        assert(testFunc(Utils.readResource("input_sort-list_01.txt")))
    }

    @Test
    fun test3() {
        assert(testFunc(Utils.readResource("input_sort-list_02.txt")))
    }

    private fun testFunc(inputStr: String): Boolean {
        val input = inputStr.toIntArray().sorted().toListNode()
        return input equalTo sortList(inputStr.toListNode())
    }
}