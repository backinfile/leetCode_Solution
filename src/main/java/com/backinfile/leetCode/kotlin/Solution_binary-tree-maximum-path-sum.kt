package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_binary-tree-maximum-path-sum` {
    fun maxPathSum(root: TreeNode?): Int {
        var result = root?.`val` ?: 0

        fun maxPath(root: TreeNode?): Int {
            if (root == null) {
                return 0
            }
            val leftValue = maxPath(root.left).coerceAtLeast(0)
            val rightValue = maxPath(root.right).coerceAtLeast(0)
            val maxSideValue = root.`val` + leftValue.coerceAtLeast(rightValue)

            result = result.coerceAtLeast(root.`val` + leftValue + rightValue)
            result = result.coerceAtLeast(maxSideValue)
            return maxSideValue
        }
        maxPath(root)
        return result
    }


    @Test
    fun test() {
        assert(42 == maxPathSum("[-10,9,20,null,null,15,7]".toTree()))
        assert(6 == maxPathSum("[1,2,3]".toTree()))
        assert(-3 == maxPathSum("[-3]".toTree()))
    }
}