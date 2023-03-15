package com.backinfile.classic

import com.backinfile.assertEqualTo
import com.backinfile.assertSortedEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntList
import org.junit.Test
import kotlin.random.Random

class SortingTree {

    private var head: TreeNode? = null

    fun add(v: Int) {
        if (head == null) {
            head = TreeNode(v)
            return
        }
        var cur = head
        while (cur != null) {
            if (cur.`val` > v) {
                if (cur.left == null) {
                    cur.left = TreeNode(v)
                    return
                }
                cur = cur.left
            } else {
                if (cur.right == null) {
                    cur.right = TreeNode(v)
                    return
                }
                cur = cur.right
            }
        }
    }

    fun find(v: Int): Boolean {
        var cur = head
        while (cur != null) {
            if (cur.`val` == v) {
                return true
            }
            cur = if (v > cur.`val`) cur.right else cur.left
        }
        return false
    }

    fun remove(v: Int): Boolean {
        if (head == null) {
            return false
        }
        val parent: TreeNode =
            if (head!!.`val` == v) TreeNode(-1).also { it.left = head }
            else findParent(v) ?: return false

        var cur = if (parent.left?.`val` == v) parent.left else parent.right

        if (cur.left == null || cur.right == null) {
            cur = cur.left ?: cur.right
        } else {
            if (cur.left.right != null) {
                val maxNode = removeMaxNode(cur.left)
                maxNode.left = cur.left
                maxNode.right = cur.right
                cur = maxNode
            } else {
                cur.left.right = cur.right
                cur = cur.left
            }
        }
        if (parent.left?.`val` == v) {
            parent.left = cur
        } else {
            parent.right = cur
        }

        if (head!!.`val` == v) {
            head = parent.left
        }
        return true
    }

    private fun findParent(v: Int): TreeNode? {
        var cur = head
        while (cur != null) {
            val next = if (v > cur.`val`) cur.right else cur.left
            if (next?.`val` == v) {
                return cur
            }
            cur = next
        }
        return null
    }

    private fun removeMaxNode(node: TreeNode): TreeNode {
        if (node.right == null) {
            return node
        }
        var cur = node
        while (true) {
            if (cur.right.right != null) {
                cur = cur.right
            } else {
                break
            }
        }
        return cur.right.also {
            cur.right = it.left
            it.left = null
        }
    }

    fun toList(): List<Int> {
        val result = arrayListOf<Int>()
        fun dfs(node: TreeNode?) {
            if (node == null) {
                return
            }
            dfs(node.left)
            result.add(node.`val`)
            dfs(node.right)
        }
        dfs(head)
        return result
    }


    @Test
    fun testAdd() {
        repeat(10) {
            val input = (1..100).map { Random.nextInt(1000) }
            val tree = SortingTree()
            input.forEach { tree.add(it) }
            assert(input.sorted() == tree.toList())
        }
    }

    @Test
    fun testFind() {
        val tree = SortingTree()
        val input = arrayListOf(1, 10, 100, 1000, -10, -1000)
        input.forEach { tree.add(it) }
        assert(tree.find(1000))
        assert(tree.find(-1000))
        assert(tree.find(100))
        assert(tree.find(1))
        assert(!tree.find(20))
    }


    @Test
    fun testRemove() {
        val tree = SortingTree()
        val input = "[868, 315, 374, 246, 827, 112, 676, 885, 892, 379]".toIntList()
        input.forEach { tree.add(it) }
        assert(input.remove(868))
    }

    @Test
    fun testRemove2() {
        repeat(10) {
            val input = (1..100).map { Random.nextInt(1000) }.toMutableList()
            val tree = SortingTree()
            input.forEach { tree.add(it) }

            val toRemove = input.random()
            assert(tree.remove(toRemove))

            input.remove(toRemove)

            assert(input.sorted() == tree.toList())
        }
    }
}