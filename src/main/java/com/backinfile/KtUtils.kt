package com.backinfile

import com.alibaba.fastjson.JSON
import com.backinfile.leetCode.kotlin.ListNode
import com.backinfile.leetCode.kotlin.equalTo
import com.backinfile.leetCode.kotlin.listNodeEquals
import com.backinfile.leetCode.kotlin.toList
import com.backinfile.lintCode.TreeNode

class KtUtils {
}


fun <T : Comparable<T>> List<List<T>>.sorted(): List<List<T>> {
    return this.sortedWith(Utils::compareList)
}

fun String.toIntArray(): IntArray {
    return Utils.toIntArray(this)
}

fun String.toStrList(): MutableList<String> {
    return Utils.toStrArray(this).toMutableList()
}
fun String.toDoubleArray(): DoubleArray {
    return Utils.toDoubleArray(this)
}
fun String.toStrListList(): MutableList<MutableList<String>> {
    return Utils.toStrArrayArray(this).map { it.toMutableList() }.toMutableList()
}

fun String.toIntList(): MutableList<Int> {
    return Utils.toIntArray(this).toMutableList()
}

fun String.toIntArrayArray(): Array<IntArray> {
    return Utils.toIntArrayArray(this)
}

fun String.toCharArrayArray(): Array<CharArray> {
    return Utils.toCharArrayArray(this)
}
fun String.toCharArray(): CharArray {
    return JSON.parseObject(this, CharArray::class.java)
}

fun String.toIntListList(): MutableList<MutableList<Int>> {
    return Utils.toIntArrayArray(this).map { it.toMutableList() }.toMutableList()
}


fun TreeNode?.toList(): List<Int?> {
    val root = this;
    val dummy = TreeNode(-1)
    val queue = java.util.ArrayDeque<TreeNode>()
    val result = ArrayList<Int?>()
    queue.add(root ?: dummy)

    while (queue.isNotEmpty()) {
        val node = queue.pop()
        if (node == dummy) {
            result.add(null)
        } else {
            result.add(node.`val`)
            queue.add(node.left ?: dummy)
            queue.add(node.right ?: dummy)
        }
    }
    while (result.isNotEmpty() && result.last() == null) {
        result.removeAt(result.lastIndex)
    }
    return result
}

fun String.toTree(): TreeNode? {
    val data = this.replace(" ", "").trim('[', ']')
    if (data == "") {
        return null
    }
    val nodesValues = data.split(',')
    val head = TreeNode(nodesValues[0].toInt())
    val queue = java.util.ArrayDeque<TreeNode>()
    queue.add(head)
    var index = 1
    while (queue.isNotEmpty()) {
        val node = queue.pop()
        if (index < nodesValues.size && nodesValues[index] != "null") {
            node.left = TreeNode(nodesValues[index].toInt())
            queue.add(node.left)
        }
        index++
        if (index < nodesValues.size && nodesValues[index] != "null") {
            node.right = TreeNode(nodesValues[index].toInt())
            queue.add(node.right)
        }
        index++
    }
    return head
}

fun TreeNode?.findNode(value: Int): TreeNode? {
    if (this == null) {
        return null
    }
    if (this.`val` == value) return this
    this.left.findNode(value)?.let { return it }
    this.right.findNode(value)?.let { return it }
    return null
}

fun String.toListNode(): ListNode? {
    return com.backinfile.leetCode.kotlin.toListNode(this);
}

fun Collection<Int>.toListNode(): ListNode? {
    return com.backinfile.leetCode.kotlin.toListNode(this.toIntArray());
}

fun ListNode?.last(): ListNode? {
    var cur = this
    while (cur?.next != null) {
        cur = cur.next
    }
    return cur
}

fun ListNode?.get(index: Int): ListNode? {
    var cur = this
    for (i in 0 until index) {
        cur = cur!!.next
    }
    return cur
}

fun Array<IntArray>.toIntListList(): MutableList<MutableList<Int>> {
    return this.map { it.toMutableList() }.toMutableList()
}