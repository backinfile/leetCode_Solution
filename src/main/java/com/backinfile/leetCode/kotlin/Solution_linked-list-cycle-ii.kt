package com.backinfile.leetCode.kotlin

import com.backinfile.get
import com.backinfile.last
import com.backinfile.toListNode
import org.junit.Test

class `Solution_linked-list-cycle-ii` {
    fun detectCycle(head: ListNode?): ListNode? {
        val hashSet = hashSetOf<ListNode>()
        var cur = head

        while (cur != null) {
            if (hashSet.contains(cur)) {
                return cur
            }

            hashSet.add(cur)
            cur = cur.next
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