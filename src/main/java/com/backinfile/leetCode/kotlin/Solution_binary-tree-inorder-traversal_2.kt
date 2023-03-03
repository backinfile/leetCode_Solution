package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toIntArray
import com.backinfile.toIntList
import com.backinfile.toIntListList
import com.backinfile.toTree
import org.junit.Test

class `Solution_binary-tree-inorder-traversal_2` {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val result = ArrayList<Int>()
        val stack = Stack(Array<TreeNode?>(105) { null })

        var cur = root
        while (true) {
            if (cur != null) {
                if (cur.left != null) {
                    stack.push(cur)
                    cur = cur.left
                } else {
                    result.add(cur.`val`)
                    cur = cur.right
                }
            } else if (!stack.isEmpty()) {
                cur = stack.pop()
                result.add(cur!!.`val`)
                cur = cur.right
            } else {
                break
            }
        }
        return result
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
        assert(testFunc("[1,null,2, null,null,3]", "[1,3,2]"))
        assert(testFunc("[]", "[]"))
        assert(testFunc("[1]", "[1]"))
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        return inorderTraversal(inputStr.toTree()) == answerStr.toIntList()
    }
}