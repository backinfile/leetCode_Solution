package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_search-in-rotated-sorted-array` {
    fun search(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) {
            return -1
        }
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = (left + right) / 2

            if (target == nums[mid]) {
                return mid
            }

            if (nums[mid] >= nums[left]) {
                if (target in nums[left]..nums[mid]) {
                    right = mid
                } else {
                    left = mid + 1
                }
            } else {
                if (target in nums[mid]..nums[right]) {
                    left = mid + 1
                } else {
                    right = mid
                }
            }
        }
        return if (target == nums[left]) left else -1
    }


    fun search_pretty(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) {
            return -1
        }

        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (target == nums[mid]) {
                return mid
            }

            if (nums[mid] > nums[left]) {
                if (target in nums[left]..nums[mid]) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            } else {
                if (target > nums[left] || target <= nums[mid]) {
                    left = mid + 1
                } else {
                    right = mid + 1
                }
            }
        }
        return -1
    }


    @Test
    fun test() {
        assert(4 == search(Utils.toIntArray("[4,5,6,7,0,1,2]"), 0))
        assert(5 == search(Utils.toIntArray("[4,5,6,7,0,1,2]"), 1))
        assert(2 == search(Utils.toIntArray("[4,5,6,7,0,1,2]"), 6))
        assert(3 == search(Utils.toIntArray("[4,5,6,7,0,1,2]"), 7))
        assert(0 == search(Utils.toIntArray("[4,5,6,7,0,1,2]"), 4))
        assert(6 == search(Utils.toIntArray("[4,5,6,7,0,1,2]"), 2))
        assert(-1 == search(Utils.toIntArray("[4,5,6,7,0,1,2]"), 3))

        assert(1 == search(Utils.toIntArray("[3,1]"), 1))
        assert(0 == search(Utils.toIntArray("[3,1]"), 3))
        assert(-1 == search(Utils.toIntArray("[3,1]"), 2))

        assert(-1 == search(Utils.toIntArray("[1]"), 0))
        assert(0 == search(Utils.toIntArray("[1]"), 1))
    }
}