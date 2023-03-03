package com.backinfile.leetCode.kotlin

import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_validate-binary-search-tree` {
    fun isValidBST(root: TreeNode?): Boolean {
        return isValid(root, Long.MAX_VALUE, Long.MIN_VALUE)
    }

    fun isValid(root: TreeNode?, max: Long, min: Long): Boolean {
        if (root == null) {
            return true
        }
        val curValue = root.`val`
        if (curValue >= max || curValue <= min) {
            return false
        }
        return isValid(root.left, curValue.toLong(), min) && isValid(root.right, max, curValue.toLong())
    }

    @Test
    fun test() {
        assert(isValidBST("[2147483647]".toTree()))
        assert(isValidBST("[2,1,3]".toTree()))
        assert(!isValidBST("[5,1,4,null,null,3,6]".toTree()))
        assert(!isValidBST("[5,4,6,null,null,3,7]".toTree()))
    }
}