package com.backinfile.leetCode.kotlin

import com.backinfile.findNode
import com.backinfile.get
import com.backinfile.lintCode.TreeNode
import com.backinfile.toListNode
import com.backinfile.toTree
import org.junit.Test
import kotlin.math.min

class `Solution_lowest-common-ancestor-of-a-binary-tree_2` {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || root == p || root == q) {
            return root
        }
        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)
        if (left != null && right != null) {
            return root
        }
        return left ?: right
    }

    @Test
    fun test() {
        assert(testFunc("[1,2]", 1, 2, 1))
        assert(testFunc("[3,5,1,6,2,0,8,null,null,7,4]", 5, 1, 3))
    }

    private fun testFunc(inputStr: String, p: Int, q: Int, answer: Int): Boolean {
        val list = inputStr.toTree()
        return list.findNode(answer) === lowestCommonAncestor(list, list.findNode(p), list.findNode(q))
    }
}