package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntArray
import com.backinfile.toTree
import org.junit.Test

class `Solution_construct-binary-tree-from-preorder-and-inorder-traversal_2` {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.isEmpty()) {
            return null
        }

        val head = TreeNode(preorder[0])
        val stack = Stack(Array<TreeNode?>(preorder.size) { null })
        stack.push(head)

        var inIndex = 0
        var i = 1
        while (i < preorder.size) {
            var cur = stack.peek()
            if (cur!!.`val` != inorder[inIndex]) {
                cur.left = TreeNode(preorder[i])
                stack.push(cur.left)
            } else {
                while (!stack.isEmpty() && stack.peek()!!.`val` == inorder[inIndex]) {
                    cur = stack.pop()!!
                    inIndex++
                }
                cur!!.right = TreeNode(preorder[i])
                stack.push(cur.right)
            }
            i++
        }
        return head
    }

    class Stack<T>(val array: Array<T>) {
        var size = 0
            private set

        fun push(v: T) {
            array[size++] = v
        }

        fun pop(): T {
            return array[--size]
        }

        fun peek(): T {
            return array[size - 1]
        }

        fun isEmpty(): Boolean {
            return size == 0
        }

        fun clear() {
            size = 0
        }
    }


    @Test
    fun test() {
        assert(
            "[3,9,20,null,null,15,7]".toTree() == buildTree(
                "[3,9,20,15,7]".toIntArray(),
                "[9,3,15,20,7]".toIntArray()
            )
        )
        assert(
            "[1,2,null,3]".toTree() == buildTree(
                "[1,2,3]".toIntArray(),
                "[3,2,1]".toIntArray()
            )
        )
        assert(
            "[1,2,null,null,3]".toTree() == buildTree(
                "[1,2,3]".toIntArray(),
                "[2,3,1]".toIntArray()
            )
        )
        assert(
            "[-1]".toTree() == buildTree(
                "[-1]".toIntArray(),
                "[-1]".toIntArray()
            )
        )
    }
}