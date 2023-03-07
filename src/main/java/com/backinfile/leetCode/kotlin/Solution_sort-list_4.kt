package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toIntArray
import com.backinfile.toListNode
import org.junit.Test

class `Solution_sort-list_4` {

    // 自低向上的归并排序
    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }

        // 移动from节点至多step步
        fun moveNext(from: ListNode?, step: Int): ListNode? {
            var movedStep = 1
            var cur: ListNode? = from
            while (cur != null && movedStep < step) {
                cur = cur!!.next
                movedStep++
            }
            return cur
        }

        val dummyHead = ListNode(-1).also { it.next = head } // 这是最终结果的上一个节点
        var length = 1 // 最小划分区域长度，区域两两合并
        while (true) {
            var cur: ListNode? = dummyHead.next
            val mergedHead = ListNode(-1) // 合并完成后放入此
            var mergedTail = mergedHead
            var mergeCnt = 0
            while (cur != null) {
                // 找第一个区域
                val leftEnd = moveNext(cur, length)
                if (leftEnd == null) { // 现有节点不足，直接返回
                    mergedTail.next = cur
                    break
                }

                // 找第二个区域
                val rightEnd = moveNext(leftEnd.next, length)
                val leftHead = cur // 第一个区域的头
                cur = rightEnd?.next?.also { rightEnd.next = null }
                val rightHead = leftEnd.next?.also { leftEnd.next = null } // 第二个区域的头

                // 合并两个区域
                val (h, t) = merge(leftHead, rightHead)
                mergedTail.next = h
                mergedTail = t!!
                mergeCnt++
            }

            dummyHead.next = mergedHead.next
            if (mergeCnt == 0) { // 如果在一次迭代中没有发生合并，迭代终止
                break
            }
            length *= 2 // 下一次迭代区域长度翻倍
        }
        return dummyHead.next
    }

    // 合并两个有序链表， 返回合并后的头节点与尾节点
    private fun merge(_left: ListNode?, _right: ListNode?): Pair<ListNode?, ListNode?> {
        var left = _left
        var right = _right

        val head = ListNode(-1)
        var tail = head
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
        while (tail.next != null) {
            tail = tail.next!!
        }
        return head.next to tail
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