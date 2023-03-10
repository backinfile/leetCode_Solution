package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_house-robber-iii` {

    private val dp = HashMap<TreeNode, Int>()
    private val dpHead = HashMap<TreeNode, Int>()
    fun rob(root: TreeNode?, head: Boolean = true): Int {
        if (root == null) {
            return 0
        }

        val history = (if (head) dpHead else dp)[root]
        if (history != null) {
            return history
        }

        return maxOf(
            rob(root.left) + rob(root.right),
            if (head) root.`val` + rob(root.left, false) + rob(root.right, false) else 0
        ).also { (if (head) dpHead else dp)[root] = it }
    }

    @Test
    fun test() {
        7 assertEqualTo rob("[3,2,3,null,3,null,1]".toTree())
        9 assertEqualTo rob("[3,4,5,1,3,null,1]".toTree())
    }
}