package com.backinfile.leetCode.kotlin

import org.junit.Test

class `Solution_min-stack` {

    class MinStack {
        var size = 0
        private var list = ArrayList<Pair<Int, Int>>()

        fun push(`val`: Int) {
            val min = if (size > 0) Math.min(list[size - 1].second, `val`) else `val`
            if (list.size > size) {
                list[size] = `val` to min
            } else {
                list.add(`val` to min)
            }
            size++
        }

        fun pop() {
            size--
        }

        fun top(): Int {
            return list[size - 1].first
        }

        fun getMin(): Int {
            return list[size - 1].second
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * var obj = MinStack()
     * obj.push(`val`)
     * obj.pop()
     * var param_3 = obj.top()
     * var param_4 = obj.getMin()
     */

    @Test
    fun test() {
        val minStack = MinStack()
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assert(-3 == minStack.getMin())
        minStack.pop()
        assert(0 == minStack.top())
        assert(-2 == minStack.getMin())
    }
}