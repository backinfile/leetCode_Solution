package com.backinfile.leetCode.kotlin

import com.backinfile.get
import com.backinfile.last
import com.backinfile.toListNode
import org.junit.Test

class `Solution_linked-list-cycle-ii_2` {
    fun detectCycle(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return null
        }
        var fast = head
        var slow = head

        while (fast != null && slow != null) {
            slow = slow.next
            fast = fast.next?.next
            if (slow === fast) {

                var chase = head
                while (true) {
                    if (chase == slow) {
                        return slow
                    }
                    chase = chase?.next
                    slow = slow?.next
                }
            }
        }
        return null
    }

    @Test
    fun test() {
        assert(testFunc("[3,2,0,-4]", 1))
        assert(testFunc("[1,2]", 0))
        assert(testFunc("[1]", -1))
    }

    private fun testFunc(inputStr: String, answerPos: Int): Boolean {
        val input = inputStr.toListNode()
        if (answerPos != -1) {
            input.last()!!.next = input.get(answerPos)
            return input.get(answerPos) == detectCycle(input)
        } else {
            return null == detectCycle(input)
        }

    }
}