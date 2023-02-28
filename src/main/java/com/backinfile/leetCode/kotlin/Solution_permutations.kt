package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class Solution_permutations {
    fun permute(nums: IntArray): List<List<Int>> {

        val result = ArrayList<List<Int>>()

        val curNums = IntArray(nums.size)

        fun next(cur: Int, index: Int) {
            if (index < 0) {
                result.add(curNums.toList())
                return
            }

            for (i in nums.indices) {
                if (cur and (1 shl i) != 0) {
                    continue
                }
                curNums[index] = nums[i]
                next(cur or (1 shl i), index - 1)
            }
        }

        next(0, nums.lastIndex)
        return result
    }

    @Test
    fun test() {
        assert(testFunc("[1,2,3]", "[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]"))
        assert(testFunc("[0,1]", "[[0,1],[1,0]]"))
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        val output = permute(Utils.toIntArray(inputStr)).sortedWith(Utils::compareList).toList()
        val answer = Utils.toIntIntList(answerStr).sortedWith(Utils::compareList).toList()
        println(output)
        return output == answer
    }
}