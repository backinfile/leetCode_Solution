package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toIntArray
import com.backinfile.toListNode
import org.junit.Test

class `Solution_sort-list_TLE` {
    fun sortList(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        fun sort(head: ListNode?): Pair<ListNode?, ListNode?> {
            if (head == null) {
                return null to null
            }

            val value = head.`val`

            var leftHead: ListNode? = null
            var rightHead: ListNode? = null

            var leftTail: ListNode? = null
            var rightTail: ListNode? = null

            var cur = head.next
            while (cur != null) {
                if (cur.`val` < value) {
                    if (leftHead == null) {
                        leftHead = cur
                        leftTail = cur
                    } else {
                        leftTail!!.next = cur
                        leftTail = cur
                    }
                } else {
                    if (rightHead == null) {
                        rightHead = cur
                        rightTail = cur
                    } else {
                        rightTail!!.next = cur
                        rightTail = cur
                    }
                }
                cur = cur.next
            }
            leftTail?.next = null
            rightTail?.next = null

            val (leftResultHead, leftResultTail) = sort(leftHead)
            val (rightResultHead, rightResultTail) = sort(rightHead)

            if (leftResultHead == null && rightResultHead == null) {
                return head to head
            }
            if (rightResultHead == null) {
                leftResultTail!!.next = head
                head.next = null
                return leftResultHead to head
            }
            if (leftResultHead == null) {
                head.next = rightResultHead
                return head to rightResultTail
            }
            leftResultTail!!.next = head
            head.next = rightResultHead
            return leftResultHead to rightResultTail
        }

        return sort(head).first
    }

    @Test
    fun test() {
        assert(testFunc("[4,2,1,3]"))
        assert(testFunc("[-1,5,3,4,0]"))
        assert(testFunc("[]"))
    }

    @Test(expected = StackOverflowError::class)
    fun test2() {
        assert(testFunc(Utils.readResource("input_sort-list_01.txt")))
    }

    private fun testFunc(inputStr: String): Boolean {
        val input = inputStr.toIntArray().sorted().toListNode()
        return input equalTo sortList(inputStr.toListNode())
    }
}