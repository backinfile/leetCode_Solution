package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_diameter-of-binary-tree` {
    fun diameterOfBinaryTree(root: TreeNode?, containsHead: Boolean = true): Int {
        if (root == null) {
            return 0
        }
        var result = 0
        fun dfs(head: TreeNode?): Int {
            if (head == null) {
                return 0
            }
            val left = dfs(head.left)
            val right = dfs(head.right)
            result = maxOf(result, left + right)
            return maxOf(left, right) + 1
        }
        dfs(root)
        return result
    }

    @Test
    fun test() {
        3 assertEqualTo diameterOfBinaryTree("[1,2,3,4,5]".toTree())
        1 assertEqualTo diameterOfBinaryTree("[1,2]".toTree())
    }
}