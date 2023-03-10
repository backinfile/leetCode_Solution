package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_house-robber-iii` {

    private val dp = arrayOf(HashMap<TreeNode, Int>(), HashMap())
    fun rob(root: TreeNode?, head: Int = 1): Int {
        if (root == null) {
            return 0
        }

        val history = dp[head][root]
        if (history != null) {
            return history
        }

        return maxOf(
            rob(root.left) + rob(root.right),
            if (head > 0) root.`val` + rob(root.left, 0) + rob(root.right, 0) else 0
        ).also { dp[head][root] = it }
    }

    @Test
    fun test() {
        7 assertEqualTo rob("[3,2,3,null,3,null,1]".toTree())
        9 assertEqualTo rob("[3,4,5,1,3,null,1]".toTree())
    }
}