package com.backinfile.leetCode.kotlin

import com.backinfile.sorted
import com.backinfile.toIntArray
import com.backinfile.toIntListList
import org.junit.Test

class Solution_subsets {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        for (flag in 0 until (1 shl nums.size)) {
            result.add(nums.indices.filter { flag and (1 shl it) != 0 }.map { nums[it] })
        }
        return result
    }

    @Test
    fun test() {
        assert(testFunc("[1,2,3]", "[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]"))
        assert(testFunc("[0]", "[[],[0]]"))
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        val output = subsets(inputStr.toIntArray())
        return output.map { it.sorted() }.sorted() == answerStr.toIntListList().map { it.sorted() }.sorted()
    }
}