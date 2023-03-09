package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_serialize-and-deserialize-binary-tree` {

    // 层序遍历
    class Codec() {
        // Encodes a URL to a shortened URL.
        fun serialize(root: TreeNode?): String {
            val queue = java.util.ArrayDeque<TreeNode>()
            val result = ArrayList<Int?>()
            queue.add(root ?: Dummy)

            while (queue.isNotEmpty()) {
                val node = queue.pop()
                if (node == Dummy) {
                    result.add(null)
                } else {
                    result.add(node.`val`)
                    queue.add(node.left ?: Dummy)
                    queue.add(node.right ?: Dummy)
                }
            }
            while (result.isNotEmpty() && result.last() == null) {
                result.removeAt(result.lastIndex)
            }
            return result.joinToString(",")
        }

        // Decodes your encoded data to tree.
        fun deserialize(data: String): TreeNode? {
            if (data == "") {
                return null
            }
            val nodesValues = data.split(',')
            val head = TreeNode(nodesValues[0].toInt())
            val queue = java.util.ArrayDeque<TreeNode>()
            queue.add(head)
            var index = 1
            while (queue.isNotEmpty()) {
                val node = queue.pop()
                if (index < nodesValues.size && nodesValues[index] != "null") {
                    node.left = TreeNode(nodesValues[index].toInt())
                    queue.add(node.left)
                }
                index++
                if (index < nodesValues.size && nodesValues[index] != "null") {
                    node.right = TreeNode(nodesValues[index].toInt())
                    queue.add(node.right)
                }
                index++
            }

            return head
        }

        companion object {
            val Dummy = TreeNode(-1)
        }
    }

    @Test
    fun test() {
        testFunc("[]")
        testFunc("[1,2,3,null,null,4,5]")
        testFunc("[1,2]")
        testFunc("[1]")
    }

    private fun testFunc(inputStr: String) {
        val tree = inputStr.toTree()
        val codec = Codec()
        val data = codec.serialize(tree)
        println("data = $data")
        val output = codec.deserialize(data)
        tree assertEqualTo output
    }
}