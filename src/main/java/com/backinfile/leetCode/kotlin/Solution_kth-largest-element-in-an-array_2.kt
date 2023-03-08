package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test

class `Solution_kth-largest-element-in-an-array_2` {

    // 利用快排的中间结果
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val findIndex = nums.size - k

        fun find(left: Int, right: Int): Int {
            val target = nums[right]
            var pos = left
            for (i in left until right) {
                if (nums[i] < target) {
                    nums.swap(i, pos++)
                }
            }
            nums.swap(pos, right)
            return when {
                pos == findIndex -> target // 能确定中间值target的最终位置就是pos
                pos < findIndex -> find(pos + 1, right)
                else -> find(left, pos - 1)
            }
        }
        return find(0, nums.lastIndex)
    }

    private fun IntArray.swap(a: Int, b: Int) {
        this[a] = this[b].also { this[b] = this[a] }
    }


    @Test
    fun test() {
        assert(5 == findKthLargest("[3,2,1,5,6,4]".toIntArray(), 2))
        assert(4 == findKthLargest("[3,2,3,1,2,4,5,5,6]".toIntArray(), 4))
    }
}