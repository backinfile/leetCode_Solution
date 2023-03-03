package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntListList
import com.backinfile.toTree
import org.junit.Test

class `Solution_binary-tree-level-order-traversal` {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = ArrayList<ArrayList<Int>>()
        fun r(node: TreeNode?, level: Int) {
            if (node == null) {
                return
            }
            val list = if (level < result.size) result[level] else ArrayList<Int>().also { result.add(it) }
            list.add(node.`val`)
            r(node.left, level + 1)
            r(node.right, level + 1)
        }

        r(root, 0)
        return result
    }


    @Test
    fun test() {
        assert(levelOrder("[3,9,20,null,null,15,7]".toTree()) == "[[3],[9,20],[15,7]]".toIntListList())
        assert(levelOrder("[1]".toTree()) == "[[1]]".toIntListList())
        assert(levelOrder("[]".toTree()) == "[]".toIntListList())
    }
}