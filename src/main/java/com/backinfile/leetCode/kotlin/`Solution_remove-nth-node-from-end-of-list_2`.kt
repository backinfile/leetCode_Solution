package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_remove-nth-node-from-end-of-list_2` {

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) {
            return null
        }
        var cur = head; // 快指针 最终要指向最后一个节点
        var dealHead = head // 慢指针 最终要指向要删除节点的上一个节点
        var index = 0
        while (cur!!.next != null) {
            cur = cur.next
            if (index >= n) { // 快指针先走n步，之后与慢指针同时走
                dealHead = dealHead!!.next
            }
            index++ // index记录快指针总共走过的步数
        }

        return when {
            index + 1 == n -> head.next // 快指针少走了一步，则此时head是应该要删除的节点
            index + 1 < n -> head       // 快指针不止少走了一步，则此时应该要删除的节点在慢指针之前，无可删除节点
            else -> {                   // 最终慢指针指向要删除节点的上一个节点
                dealHead!!.next = dealHead.next!!.next
                head
            }
        }
    }

    @Test
    fun test() {
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2,3,4,5]"), 2), toListNode("[1,2,3,5]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1]"), 1), toListNode("[]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2]"), 1), toListNode("[1]")))

    }

    @Test
    fun test2() {
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2,3,4,5]"), 1), toListNode("[1,2,3,4]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2,3,4,5]"), 5), toListNode("[2,3,4,5]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[]"), 1), toListNode("[]")))
        assert(listNodeEquals(removeNthFromEnd(toListNode("[1,2,3,4,5]"), 6), toListNode("[1,2,3,4,5]")))
    }
}