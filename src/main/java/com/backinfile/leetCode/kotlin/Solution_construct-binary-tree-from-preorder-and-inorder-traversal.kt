package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntArray
import com.backinfile.toTree
import org.junit.Test

class `Solution_construct-binary-tree-from-preorder-and-inorder-traversal` {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty()) {
            return null
        }
        fun build(start: Int, end: Int, iStart: Int, iEnd: Int): TreeNode? {
            if (start > end) {
                return null
            }

            val curValue = preorder[start]
            val head = TreeNode(curValue)
            if (start == end) {
                return head
            }

            val headIndex = inorder.indexOf(curValue) // 左子树的最右侧index
            val leftSize = headIndex - iStart
            if (leftSize > 0) {
                head.left = build(start + 1, start + 1 + leftSize - 1, iStart, headIndex - 1)
                head.right = build(start + 1 + leftSize - 1 + 1, end, headIndex + 1, iEnd)
            } else {
                head.right = build(start + 1, end, headIndex + 1, iEnd)
            }
            return head
        }

        return build(0, preorder.lastIndex, 0, inorder.lastIndex)
    }


    @Test
    fun test() {
        assert(
            "[1,2,null,3]".toTree() == buildTree(
                "[1,2,3]".toIntArray(),
                "[3,2,1]".toIntArray()
            )
        )
        assert(
            "[1,2,null,null,3]".toTree() == buildTree(
                "[1,2,3]".toIntArray(),
                "[2,3,1]".toIntArray()
            )
        )
        assert(
            "[3,9,20,null,null,15,7]".toTree() == buildTree(
                "[3,9,20,15,7]".toIntArray(),
                "[9,3,15,20,7]".toIntArray()
            )
        )
        assert(
            "[-1]".toTree() == buildTree(
                "[-1]".toIntArray(),
                "[-1]".toIntArray()
            )
        )
    }
}