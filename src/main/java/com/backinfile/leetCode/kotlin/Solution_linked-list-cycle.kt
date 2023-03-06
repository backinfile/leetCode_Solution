package com.backinfile.leetCode.kotlin

import com.backinfile.get
import com.backinfile.last
import com.backinfile.toListNode
import org.junit.Test

class `Solution_linked-list-cycle` {
    fun hasCycle(head: ListNode?): Boolean {
        if (head?.next == null) {
            return false
        }
        var fast = head
        var slow = head

        while (fast != null && slow != null) {
//            println("fast=${fast.`val`} slow=${slow.`val`}")
            slow = slow.next
            fast = fast.next?.next
            if (slow === fast) {
                return true
            }
        }
        return false
    }

    @Test
    fun test() {
        assert(testFunc("[3,2,0,-4]", 1, true))
        assert(testFunc("[1,2]", 0, true))
        assert(testFunc("[1]", -1, false))
    }

    private fun testFunc(inputStr: String, answerPos: Int, answer: Boolean): Boolean {
        val input = inputStr.toListNode()
        if (answerPos != -1) {
            input.last()!!.next = input.get(answerPos)
        }

        return answer == hasCycle(input)
    }
}