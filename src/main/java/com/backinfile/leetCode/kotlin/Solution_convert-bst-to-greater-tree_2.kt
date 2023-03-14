package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.lintCode.TreeNode
import com.backinfile.toTree
import org.junit.Test

class `Solution_convert-bst-to-greater-tree_2` {
    fun convertBST(root: TreeNode?): TreeNode? {

        var sum = 0
        var cur = root

        fun visited(node:TreeNode) {
            sum += node.`val`
            node.`val` = sum
        }

        while (cur != null) {
            if (cur.right != null) {
                var mostLeft = cur.right
                while (mostLeft.left != null && mostLeft.left != cur) {
                    mostLeft = mostLeft.left
                }
                if (mostLeft.left == null) {
                    mostLeft.left = cur
                    cur = cur.right
                } else {
                    visited(cur)
                    mostLeft.left = null
                    cur = cur.left
                }
            } else {
                visited(cur)
                cur = cur.left
            }
        }
        return root
    }

    @Test
    fun test() {
        "[103,null,589,657,307,650,608,444,227,503,661,631,null,569,380,268,49,353,600,656,664,650,620,null,473,414,345,null,null,96,null,197,386,549,629,null,null,664,663,null,641,null,null,500,null,null,null,null,null,141,null,151,240,null,477,528,567,615,642,null,null,null,661,null,null,547,null,185,null,null,null,null,280,449,null,null,null,null,584,null,null,null,null,null,null,null,524,null,null,null,317,418]".toTree() assertEqualTo convertBST("[-48,null,20,7,39,-6,19,30,42,-25,-3,11,null,22,35,41,49,-33,-15,-5,1,9,12,null,29,34,38,null,null,47,null,-43,-32,-18,-13,null,null,0,2,null,10,null,null,27,null,null,null,null,null,45,null,-46,-40,null,-26,-21,-17,-14,-8,null,null,null,4,null,null,23,null,44,null,null,null,null,-37,-28,null,null,null,null,-16,null,null,null,null,null,null,null,24,null,null,null,-36,-31]".toTree())
        "[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]".toTree() assertEqualTo convertBST("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]".toTree())
        "[1,null,1]".toTree() assertEqualTo convertBST("[0,null,1]".toTree())
        "[3,3,2]".toTree() assertEqualTo convertBST("[1,0,2]".toTree())
        "[7,9,4,10]".toTree() assertEqualTo convertBST("[3,2,4,1]".toTree())
    }
}