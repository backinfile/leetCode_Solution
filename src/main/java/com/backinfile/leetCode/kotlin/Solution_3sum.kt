package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class Solution_3sum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        nums.sort()
        for (index in nums.indices) {
            val target = nums[index]
            if (index + 1 < nums.size && nums[index + 1] == target) {
                continue
            }

            var left = 0
            var right = index - 1
            while (left < right) {
                val leftValue = nums[left]
                val rightValue = nums[right]
                val sum = leftValue + rightValue + target
                when {
                    sum > 0 -> right--
                    sum < 0 -> left++
                    else -> {
                        result.add(listOf(leftValue, rightValue, target))
                        do left++ while (left < right && nums[left] == leftValue);
                        do right-- while (left < right && nums[right] == rightValue)
                    }
                }
            }
        }
        return result
    }

    @Test
    fun test() {
        assert(equals(threeSum(Utils.toIntArray("[-1,0,1,2,-1,-4]")), Utils.toIntIntArray("[[-1,-1,2],[-1,0,1]]")))
        assert(equals(threeSum(Utils.toIntArray("[0,1,1]")), Utils.toIntIntArray("[]")))
        assert(equals(threeSum(Utils.toIntArray("[0,0,0]")), Utils.toIntIntArray("[[0,0,0]]")))
    }

    private fun equals(output: List<List<Int>>, answer: Array<IntArray>): Boolean {

        val l1 = output.map { it.sorted() }
            .sortedWith { o1, o2 -> for (i in o1.indices) if (o1[i] != o2[i]) return@sortedWith o1[i] - o2[i]; 0 }
        val l2 = answer.map { it.sorted() }
            .sortedWith { o1, o2 -> for (i in o1.indices) if (o1[i] != o2[i]) return@sortedWith o1[i] - o2[i]; 0 }
        return l1 == l2
    }
}