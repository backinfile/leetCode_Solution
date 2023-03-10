package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test
import kotlin.random.Random

class `Solution_longest-increasing-subsequence_3` {

    // 返回递增队列

    fun lengthOfLIS(nums: IntArray): List<Int> {
        val piles = ArrayList<ArrayList<Int>>()

        loop@ for (i in nums.indices) {
            val cur = nums[i]
            for (pile in piles) {
                if (pile.last() >= cur) {
                    pile.add(cur)
                    continue@loop
                }
            }
            piles.add(arrayListOf(cur))
        }

//        println(piles)

        val path = ArrayList<Int>()
        path.add(piles.last().first())
        for (i in piles.lastIndex - 1 downTo 0) {
            for (n in piles[i]) {
                if (path.last() > n) {
                    path.add(n)
                    break
                }
            }
        }
        path.reverse()
        return path
    }

    @Test
    fun test() {
        val solution = `Solution_longest-increasing-subsequence_2`()

        for (i in 0..1000) {
            val intArray = (-1..Random.nextInt(100)).map { Random.nextInt(10000) }.toIntArray()
            val lis = lengthOfLIS(intArray)
            solution.lengthOfLIS(intArray) assertEqualTo lis.size
            var index = -1
            for (n in lis) {
                val nextIndex = indexOf(intArray, index, n)
                assert(nextIndex > index)
                index = nextIndex
            }
        }
    }

    private fun indexOf(arr: IntArray, from: Int, value: Int): Int {
        for (i in from.coerceAtLeast(0) until arr.size) {
            if (arr[i] == value) {
                return i
            }
        }
        return -1
    }
}