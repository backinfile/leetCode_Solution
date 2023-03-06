package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntArray
import com.backinfile.toTree
import org.junit.Test

class `Solution_construct-binary-tree-from-preorder-and-inorder-traversal_3` {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty()) {
            return null
        }
        fun build(preRange: IntRange, inRange: IntRange): TreeNode? {
            if (preRange.isEmpty()) {
                return null
            }
            val start = preRange.first
            val end = preRange.last
            val curValue = preorder[start]
            val head = TreeNode(curValue)
            if (start == end) {
                return head
            }

            val headIndex = inorder.indexOf(curValue) // 左子树的最右侧index+1
            val leftSize = headIndex - inRange.first // 左侧子树的所有节点数目
            head.left = build(start + 1 until start + 1 + leftSize, inRange.first until headIndex)
            head.right = build(start + 1 + leftSize..end, headIndex + 1..inRange.last)
            return head
        }
        return build(preorder.indices, inorder.indices)
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