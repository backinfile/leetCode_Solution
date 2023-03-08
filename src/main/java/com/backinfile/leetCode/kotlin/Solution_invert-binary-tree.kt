package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode

class `Solution_invert-binary-tree` {
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }
        root.left = root.right.also { root.right = root.left }
        invertTree(root.left)
        invertTree(root.right)
        return root
    }
}