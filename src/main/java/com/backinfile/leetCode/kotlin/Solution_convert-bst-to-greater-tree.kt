package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_convert-bst-to-greater-tree` {
    fun convertBST(root: TreeNode?): TreeNode? {
        dfs(root, 0)
        return root
    }

    private fun dfs(head: TreeNode?, cur: Int): Int {
        if (head == null) {
            return cur
        }
        head.`val` += dfs(head.right, cur)
        return dfs(head.left, head.`val`)
    }

    @Test
    fun test() {
        "[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]".toTree() assertEqualTo convertBST("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]".toTree())
        "[1,null,1]".toTree() assertEqualTo convertBST("[0,null,1]".toTree())
        "[3,3,2]".toTree() assertEqualTo convertBST("[1,0,2]".toTree())
        "[7,9,4,10]".toTree() assertEqualTo convertBST("[3,2,4,1]".toTree())
    }
}