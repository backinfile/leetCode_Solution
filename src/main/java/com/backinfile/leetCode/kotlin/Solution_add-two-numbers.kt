package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class `Solution_add-two-numbers` {

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var n1 = l1;
        var n2 = l2;

        if (n1 == null || n2 == null) {
            return ListNode(0)
        }

        var add:Int = 0;
        val result = ListNode(0)
        var head = result
        while (!(n1 == null && n2 == null)) {
            if (n1 != null) {
                add += n1.`val`
                n1 = n1.next
            }
            if (n2 != null) {
                add += n2.`val`
                n2 = n2.next
            }
            head.next = ListNode(add % 10)
            head = head.next!!
            add /= 10
        }
        if (add > 0) {
            head.next = ListNode(add)
        }
        return result.next
    }


    @Test
    fun test() {
        assert(
            equal(
                toListNode("[7,0,8]"),
                addTwoNumbers(toListNode("[2,4,3]"), toListNode("[5,6,4]"))
            )
        )
        assert(
            equal(
                toListNode("[0]"),
                addTwoNumbers(toListNode("[0]"), toListNode("[0]"))
            )
        )
        assert(
            equal(
                toListNode("[8,9,9,9,0,0,0,1]"),
                addTwoNumbers(toListNode("[9,9,9,9,9,9,9]"), toListNode("[9,9,9,9]"))
            )
        )
    }

    private fun equal(l1: ListNode?, l2: ListNode?): Boolean {
        var n1: ListNode? = l1;
        var n2: ListNode? = l2;
        while (true) {
            if (n1 != null && n2 != null) {
                if (n1.`val` != n2.`val`) {
                    return false
                }
                n1 = n1.next
                n2 = n2.next
            } else return n1 == null && n2 == null
        }
    }



}