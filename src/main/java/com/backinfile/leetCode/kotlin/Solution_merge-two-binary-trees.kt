package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_merge-two-binary-trees` {
    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (root1 == null || root2 == null) {
            return root1 ?: root2
        }

        val head = TreeNode(root1.`val` + root2.`val`)
        head.left = mergeTrees(root1.left, root2.left)
        head.right = mergeTrees(root1.right, root2.right)
        return head
    }

    @Test
    fun test() {
        "[3,4,5,5,4,null,7]".toTree() assertEqualTo mergeTrees("[1,3,2,5]".toTree(), "[2,1,3,null,4,null,7]".toTree())
        "[2,2]".toTree() assertEqualTo mergeTrees("[1]".toTree(), "[1,2]".toTree())
    }
}