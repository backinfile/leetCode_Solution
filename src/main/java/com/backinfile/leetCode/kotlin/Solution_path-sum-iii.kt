package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_path-sum-iii` {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) {
            return 0
        }
        var result = 0
        fun dfs(head: TreeNode?, path: MutableList<Long>) {
            if (head == null) {
                return
            }
            path.add(0)
            for (i in path.indices) path[i] += head.`val`.toLong()

            result += path.count { it == targetSum.toLong() }

            dfs(head.left, path)
            dfs(head.right, path)


            path.removeAt(path.lastIndex)
            for (i in path.indices) path[i] -= head.`val`.toLong()
        }
        dfs(root, arrayListOf())
        return result
    }

    @Test
    fun test() {
        0 assertEqualTo pathSum("[1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000]".toTree(), 0)
        3 assertEqualTo pathSum("[10,5,-3,3,2,null,11,3,-2,null,1]".toTree(), 8)
        3 assertEqualTo pathSum("[5,4,8,11,null,13,4,7,2,null,null,5,1]".toTree(), 22)
    }
}