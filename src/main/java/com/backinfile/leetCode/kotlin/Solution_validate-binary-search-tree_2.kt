package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_validate-binary-search-tree_2` {

    private var min = Long.MIN_VALUE
    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) {
            return true
        }
        if (!isValidBST(root.left)) {
            return false
        }
        if (root.`val` <= min) {
            return false
        }
        min = root.`val`.toLong()
        return isValidBST(root.right)
    }

    private fun resetMin() {
        min = Long.MIN_VALUE
    }

    @Test
    fun test() {
        resetMin()
        assert(isValidBST("[2,1,3]".toTree()))
        resetMin()
        assert(isValidBST("[2147483647]".toTree()))
        resetMin()
        assert(!isValidBST("[5,1,4,null,null,3,6]".toTree()))
        resetMin()
        assert(!isValidBST("[5,4,6,null,null,3,7]".toTree()))
    }
}