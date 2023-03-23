package com.backinfile

import com.backinfile.leetCode.kotlin.ListNode
import com.backinfile.leetCode.kotlin.equalTo
import com.backinfile.leetCode.kotlin.toList
import com.backinfile.lintCode.TreeNode

class KtAssertions {
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

infix fun Number.assertEqualTo(other: Number) {
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
        println("answer=${this?.toList()}\noutput=${other?.toList()}")
        assert(false)
    }
}

infix fun ListNode?.assertEqualTo(other: ListNode?) {
    if (this equalTo other) {
        assert(true)
    } else {
        println("answer=${this.toList()}\noutput=${other.toList()}")
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