package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test
import java.util.*

class `Solution_flatten-binary-tree-to-linked-list` {
    fun flatten(root: TreeNode?): Unit {
        if (root == null) {
            return
        }

        val stack = Stack<TreeNode>()
        var result = root

        var cur = root.left
        stack.add(root.right)

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                result!!.left = null
                result.right = cur
                result = cur

                stack.add(cur.right)
                cur = cur.left
            } else {
                cur = stack.pop()
            }
        }
    }


    @Test
    fun test() {
        val node = "[1,2,5,3,4,null,6]".toTree()
        flatten(node)
        println(node!!.toList())
    }
}