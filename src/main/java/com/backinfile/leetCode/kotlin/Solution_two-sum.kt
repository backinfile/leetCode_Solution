package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test
import java.util.*

class `Solution_two-sum` {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        nums.forEachIndexed { index, num ->
            if (map.containsKey(target - num)) {
                return intArrayOf(map[target - num]!!, index);
            }
            map[num] = index;
        }
        return intArrayOf()
    }


    @Test
    fun test() {
        assert(Arrays.equals(twoSum(Utils.toIntArray("[2,7,11,15]"), 9), Utils.toIntArray("[0,1]")));
        assert(Arrays.equals(twoSum(Utils.toIntArray("[3,2,4]"), 6), Utils.toIntArray("[1,2]")));
        assert(Arrays.equals(twoSum(Utils.toIntArray("[3,3]"), 6), Utils.toIntArray("[0,1]")));
    }
}