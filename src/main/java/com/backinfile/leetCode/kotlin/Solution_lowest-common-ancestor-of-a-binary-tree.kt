package com.backinfile.leetCode.kotlin

import com.backinfile.findNode
import com.backinfile.get
import com.backinfile.lintCode.TreeNode
import com.backinfile.toListNode
import com.backinfile.toTree
import org.junit.Test
import kotlin.math.min

class `Solution_lowest-common-ancestor-of-a-binary-tree` {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }

        val pathP = ArrayList<TreeNode>()
        val pathQ = ArrayList<TreeNode>()

        findNode(root, p, pathP)
        findNode(root, q, pathQ)

        val minSize = Math.min(pathP.size, pathQ.size)
        for (i in 0 until minSize) {
            if (pathP[pathP.size - 1 - i] !== pathQ[pathQ.size - 1 - i]) {
                return pathP[pathP.size - i]
            }
        }
        return pathP[pathP.size - minSize]
    }

    private fun findNode(root: TreeNode, p: TreeNode?, path: MutableList<TreeNode>): Boolean {
        if (root == p) {
            path.add(root)
            return true
        }
        if (root.left != null && findNode(root.left, p, path)) {
            path.add(root)
            return true
        }

        if (root.right != null && findNode(root.right, p, path)) {
            path.add(root)
            return true
        }
        return false
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