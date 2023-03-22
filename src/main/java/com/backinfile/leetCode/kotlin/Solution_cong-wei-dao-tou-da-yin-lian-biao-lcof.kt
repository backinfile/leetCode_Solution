package com.backinfile.leetCode.kotlin

class `Solution_cong-wei-dao-tou-da-yin-lian-biao-lcof` {
    fun reversePrint(head: ListNode?): IntArray {
        return head.toSequence().toList().toIntArray().also { it.reverse() }
    }

    fun ListNode?.toSequence() = sequence {
        var cur = this@toSequence
        while (cur != null) {
            yield(cur.`val`)
            cur = cur.next
        }
    }

}