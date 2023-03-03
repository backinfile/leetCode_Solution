package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntArray
import com.backinfile.toIntList
import com.backinfile.toIntListList
import com.backinfile.toTree
import org.junit.Test

class `Solution_binary-tree-inorder-traversal` {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val result = ArrayList<Int>()

        fun r(node: TreeNode?) {
            if (node == null) {
                return
            }
            r(node.left)
            result.add(node.`val`)
            r(node.right)
        }

        r(root)
        return result
    }

    @Test
    fun test() {
        assert(testFunc("[1,null,2, null,null,3]", "[1,3,2]"))
        assert(testFunc("[]", "[]"))
        assert(testFunc("[1]", "[1]"))
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        return inorderTraversal(inputStr.toTree()) == answerStr.toIntList()
    }
}