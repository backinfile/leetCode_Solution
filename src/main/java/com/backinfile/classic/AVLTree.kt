package com.backinfile.classic

import com.backinfile.lintCode.TreeNode
import kotlin.math.abs

class AVLTree : SortingTree() {
    var head: TreeNode? = null


    override fun afterAdd(node: TreeNode) {
        super.afterAdd(node)

        fun dfs(cur: TreeNode?) {
            if (cur == null) return

            if (cur.`val` > node.`val`) {
                dfs(cur.left)
            } else {
                dfs(cur.right)
            }


        }
        dfs(node)
    }

    fun checkAVL(node: TreeNode? = head): Int {
        if (node == null) {
            return 0
        }
        val left = checkAVL(node.left)
        val right = checkAVL(node.right)
        if (left == -1 || right == -1 || abs(left - right) > 1) {
            return -1
        }
        return maxOf(left, right) + 1
    }
}