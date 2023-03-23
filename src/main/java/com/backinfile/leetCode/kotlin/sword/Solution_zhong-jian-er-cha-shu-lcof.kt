package com.backinfile.leetCode.kotlin.sword

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntArray
import com.backinfile.toTree
import org.junit.Test

class `Solution_zhong-jian-er-cha-shu-lcof` {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty()) return null

        fun dfs(pFrom: Int, pTo: Int, iFrom: Int): TreeNode? {
            if (pFrom > pTo) return null
            val headValue = preorder[pFrom]
            val head = TreeNode(headValue)
            if (pFrom == pTo) return head

            val headIndexInInorder = inorder.indexOf(headValue)
            val leftCnt = headIndexInInorder - iFrom
            head.left = dfs(pFrom + 1, pFrom + leftCnt, iFrom)
            head.right = dfs(pFrom + leftCnt + 1, pTo, iFrom + leftCnt + 1)
            return head
        }
        return dfs(0, preorder.lastIndex, 0)
    }

    @Test
    fun test() {
        "[3,9,20,null,null,15,7]".toTree() assertEqualTo buildTree(
            "[3,9,20,15,7]".toIntArray(),
            "[9,3,15,20,7]".toIntArray()
        )
        "[-1]".toTree() assertEqualTo buildTree(
            "[-1]".toIntArray(),
            "[-1]".toIntArray()
        )
    }
}