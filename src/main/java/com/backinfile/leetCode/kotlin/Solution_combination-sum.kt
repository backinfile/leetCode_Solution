package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_combination-sum` {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        val used = IntArray(candidates.size)

        fun r(curIndex: Int, curSum: Int) {
            if (curSum > target) {
                return
            }
            if (curIndex == 0) {
                if (target == curSum) {
                    val r = arrayListOf<Int>()
                    used.forEachIndexed { index, i -> repeat(i) { r.add(candidates[index]) } }
                    result.add(r)
                }
                return
            }
            val curValue = candidates[curIndex - 1]
            for (i in 0..(target - curSum) / curValue) {
                used[curIndex - 1] = i
                r(curIndex - 1, i * curValue + curSum)
            }
        }

        r(candidates.size, 0)
        return result
    }


    @Test
    fun test() {
        assert(testFunc("[2,3,6,7]", 7, "[[2,2,3],[7]]"))
        assert(testFunc("[2,3,5]", 8, "[[2,2,2,2],[2,3,3],[3,5]]"))
        assert(testFunc("[2]", 1, "[]"))
    }


    private fun testFunc(inputStr: String, target: Int, answerString: String): Boolean {
        val output = combinationSum(Utils.toIntArray(inputStr), target).map { it.sorted() }.toSet()
        val answer = Utils.toIntIntArray(answerString).map { it.sorted() }.toSet()
        println(output)
        return output == answer
    }
}