package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_maximum-depth-of-binary-tree` {
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right))
    }

    @Test
    fun test() {
        assert(maxDepth("[3,9,20,null,null,15,7]".toTree()) == 3)
    }
}