package com.backinfile

import com.backinfile.leetCode.kotlin.ListNode
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

fun String.toIntListList(): MutableList<MutableList<Int>> {
    return Utils.toIntArrayArray(this).map { it.toMutableList() }.toMutableList()
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

infix fun <T : Iterable<*>> T.assertEqualTo(other: T) {
    if (this == other) {
        assert(true)
    } else {
        println("answer=$this\noutput=$other")
        assert(false)
    }
}

infix fun <T : Iterable<V>, V : Comparable<V>> T.assertSortedEqualTo(other: T) {
    val a = this.sorted()
    val b = other.sorted()
    if (a == b) {
        assert(true)
    } else {
        println("answer=$a\noutput=$b")
        assert(false)
    }
}

infix fun Int.assertEqualTo(other: Int) {
    if (this == other) {
        assert(true)
    } else {
        println("answer=$this\noutput=$other")
        assert(false)
    }
}

infix fun TreeNode?.assertEqualTo(other: TreeNode?) {
    if (this == other) {
        assert(true)
    } else {
        println("answer=$this\noutput=$other")
        assert(false)
    }
}

infix fun IntArray.assertEqualTo(other: IntArray) {
    val a = this.toList()
    val b = other.toList()
    if (a == b) {
        assert(true)
    } else {
        println("answer=$a\noutput=$b")
        assert(false)
    }
}

infix fun String.assertEqualTo(other: String) {
    val a = this
    val b = other
    if (a == b) {
        assert(true)
    } else {
        println("answer=$a\noutput=$b")
        assert(false)
    }
}


infix fun Boolean.assertEqualTo(other: Boolean) {
    val a = this
    val b = other
    if (a == b) {
        assert(true)
    } else {
        println("answer=$a\noutput=$b")
        assert(false)
    }
}

infix fun IntArray.assertSortedEqualTo(other: IntArray) {
    val a = this.sorted()
    val b = other.sorted()
    if (a == b) {
        assert(true)
    } else {
        println("answer=$a\noutput=$b")
        assert(false)
    }
}