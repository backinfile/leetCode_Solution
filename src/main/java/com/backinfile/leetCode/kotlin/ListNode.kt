package com.backinfile.leetCode.kotlin

import com.backinfile.Utils

class ListNode(var `val`: Int) {
    var next: ListNode? = null;

    override fun equals(other: Any?): Boolean {
        var n1: ListNode? = this;
        var n2: ListNode? = other as ListNode?;
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

    override fun hashCode(): Int {
        var result = `val`
        result = 31 * result + (next?.hashCode() ?: 0)
        return result
    }
}


fun toListNode(str: String): ListNode? {
    val array = Utils.toIntArray(str)
    if (array.isEmpty()) {
        return null
    }
    val nodes = array.map { ListNode(it) }
    nodes.forEachIndexed { index, node ->
        if (index + 1 < nodes.size) {
            node.next = nodes[index + 1]
        }
    }
    return nodes[0]
}

fun toListNode(array: IntArray): ListNode? {
    if (array.isEmpty()) {
        return null
    }
    val nodes = array.map { ListNode(it) }
    nodes.forEachIndexed { index, node ->
        if (index + 1 < nodes.size) {
            node.next = nodes[index + 1]
        }
    }
    return nodes[0]
}

fun listNodeEquals(l1: ListNode?, l2: ListNode?): Boolean {
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
