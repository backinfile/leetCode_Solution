package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_path-sum-iii_3` {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) {
            return 0
        }
        return dfs(root, targetSum.toLong()) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum)
    }

    // 遍历包含当前节点的所有子树
    private fun dfs(head: TreeNode?, target: Long): Int {
        if (head == null) {
            return 0
        }
        return dfs(head.left, target - head.`val`) +
                dfs(head.right, target - head.`val`) +
                if (head.`val`.toLong() == target) 1 else 0
    }

    @Test
    fun test() {
        0 assertEqualTo pathSum(
            "[1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000]".toTree(),
            0
        )
        3 assertEqualTo pathSum("[10,5,-3,3,2,null,11,3,-2,null,1]".toTree(), 8)
        3 assertEqualTo pathSum("[5,4,8,11,null,13,4,7,2,null,null,5,1]".toTree(), 22)
    }
}