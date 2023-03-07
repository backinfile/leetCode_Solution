package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import com.backinfile.toIntArray
import com.backinfile.toListNode
import org.junit.Test
import java.util.ArrayList
import java.util.LinkedList

class `Solution_sort-list` {
    fun sortList(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }
        val arrayList = ArrayList<ListNode>()
        var cur = head
        while (cur != null) {
            arrayList.add(cur)
            cur = cur.next
        }

        arrayList.sortBy { it.`val` }

        val result = arrayList[0]
        var tail = result

        for (i in 1 until arrayList.size) {
            tail.next = arrayList[i]
            tail = tail.next!!
        }
        tail.next = null
        return result
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