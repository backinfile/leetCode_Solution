package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import com.backinfile.toIntList
import org.junit.Test

class `Solution_sliding-window-maximum_2` {

    // 单调递减序列
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {

        val queue = java.util.ArrayDeque<Int>(k) // 存放数字index

        fun Int.num(): Int {
            return nums[this]
        }

        for (i in 0 until k - 1) {
            // 保持序列递减特性, 小于当前的值用不到了，直接丢掉
            // 因为求区间的最大值，只需要在队列里保存最大的，就能保证i~i+k之间的最大值不小于queue.last().num()
            while (queue.isNotEmpty() && queue.last.num() <= i.num()) {
                queue.removeLast()
            }
            queue.addLast(i)
        }

        val result = IntArray(nums.size - k + 1)

        for (i in k - 1 until nums.size) {
            // 保持序列递减特性
            while (queue.isNotEmpty() && queue.last.num() <= i.num()) {
                queue.removeLast()
            }
            queue.addLast(i)

            // 删除超出区域长的元素
            while (queue.isNotEmpty() && queue.first <= i - k) {
                queue.removeFirst()
            }
            result[i - k + 1] = queue.first.num()
        }
        return result
    }


    @Test
    fun test() {
        "[3,3,5,5,6,7]".toIntList() assertEqualTo maxSlidingWindow("[1,3,-1,-3,5,3,6,7]".toIntArray(), 3).toList()
        "[1]".toIntList() assertEqualTo maxSlidingWindow("[1]".toIntArray(), 1).toList()
    }
}