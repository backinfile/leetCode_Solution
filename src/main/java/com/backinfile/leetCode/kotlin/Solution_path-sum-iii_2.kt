package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_path-sum-iii_2` {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) {
            return 0
        }

        val hashMap = hashMapOf(0L to 1) // 保存前缀

        var result = 0
        fun dfs(head: TreeNode?, cur: Long) {
            if (head == null) {
                return
            }

            val total = cur + head.`val`.toLong()

            // 后缀=total - 前缀=targetSum
            // 所以 前缀=total - targetSum
            result += hashMap[total - targetSum]?:0

            hashMap.compute(total) { _, oldValue -> (oldValue ?: 0) + 1 }

            dfs(head.left, total)
            dfs(head.right, total)

            hashMap.compute(total) { _, oldValue -> (oldValue ?: 0) - 1 }
        }
        dfs(root, 0)
        return result
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