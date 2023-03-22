package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntArray
import com.backinfile.toTree
import org.junit.Test

class `Solution_zhong-jian-er-cha-shu-lcof_2` {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty()) return null

        val stack = java.util.Stack<TreeNode>()
        var index = 0
        val head = TreeNode(preorder[0])
        for (num in preorder) {
            if (stack.isEmpty()) {
                stack.add(head)
                continue
            }
            if (stack.peek().`val` == inorder[index]) {
                var cur = stack.peek()
                do {
                    cur = stack.peek()
                    stack.pop()
                    index++
                } while (stack.isNotEmpty() && stack.peek().`val` == inorder[index])
                cur.right = TreeNode(num)
                stack.add(cur.right)
            } else {
                stack.peek().left = TreeNode(num)
                stack.add(stack.peek().left)
            }
        }
        return head
    }

    @Test
    fun test() {
        "[3,9,20,null,null,15,7]".toTree() assertEqualTo buildTree(
            "[3,9,20,15,7]".toIntArray(),
            "[9,3,15,20,7]".toIntArray()
        )
        "[-1]".toTree() assertEqualTo buildTree(
            "[-1]".toIntArray(),
            "[-1]".toIntArray()
        )
    }
}