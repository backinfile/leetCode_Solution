package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_symmetric-tree` {
    fun isSymmetric(root: TreeNode?): Boolean {
        return root == null || r(root.left, root.right)
    }

    private fun r(node1: TreeNode?, node2: TreeNode?): Boolean {
        if (node1 == null && node2 == null) {
            return true
        }
        if (node1 == null || node2 == null) {
            return false
        }
        return node1.`val` == node2.`val` &&  r(node1.left, node2.right) && r(node1.right, node2.left)
    }

    @Test
    fun test() {
        assert(!isSymmetric("[1,2,3]".toTree()))
        assert(isSymmetric("[1,2,2,3,4,4,3]".toTree()))
        assert(!isSymmetric("[1,2,2,null,3,null,3]".toTree()))
    }
}