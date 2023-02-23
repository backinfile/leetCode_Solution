package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_container-with-most-water` {
    fun maxArea(height: IntArray): Int {

        // 双指针
        var left = 0
        var right = height.lastIndex
        var maxHeight = 0
        while (left < right) {
            val curHeight = height[left].coerceAtMost(height[right])
            maxHeight = maxHeight.coerceAtLeast(curHeight * (right - left))

            // maxHeight取决于left与right中最低的一个，所以只移动最低的哪个即可
            // 若移动最高的那个必定导致maxHeight降低, 所以直接剪枝，不用递归
            if (height[left] < height[right]) left++ else right--
        }
        return maxHeight;
    }

    @Test
    fun test() {
        assert(maxArea(Utils.toIntArray("[1,8,6,2,5,4,8,3,7]")) == 49)
        assert(maxArea(Utils.toIntArray("[1,1]")) == 1)
    }
}