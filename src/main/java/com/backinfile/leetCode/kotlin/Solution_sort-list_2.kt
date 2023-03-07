package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toIntArray
import com.backinfile.toListNode
import org.junit.Test

class `Solution_sort-list_2` {
    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        if (head.next!!.next == null) {
            if (head.`val` < head.next!!.`val`) {
                return head
            }
            head.next!!.next = head
            return head.next.also { head.next = null }
        }

        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow!!.next
            fast = fast.next?.next
        }

        var right = sortList(slow?.next?.also { slow.next = null })
        var left = sortList(head)

        val result = MutableDummy
        var tail = result

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
        return result.next
    }

    companion object {
        private val MutableDummy = ListNode(-1)
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