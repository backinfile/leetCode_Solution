package com.backinfile.leetCode.kotlin

import org.junit.Test
import kotlin.math.min

class `Solution_min-stack_2` {

    class MinStack {
        private var stack = java.util.Stack<Long>() // 存放 stack.top = 当前val - 上次minValue
        private var minValue: Int = 0 // 当前最小值

        fun push(`val`: Int) {
            if (stack.isEmpty()) {
                stack.add(0)
                minValue = `val`
            } else {
                val diff = `val`.toLong() - minValue
                stack.add(diff)
                if (diff >= 0) { // val更大，min取当前minValue
                } else { // minValue更大，min取当前值
                    minValue = `val`
                }
            }
        }

        // 获取上次的最小值
        private fun getLastMinValue(): Int {
            val diff = stack.peek()
            if (diff >= 0) { // val更大，说明上次取min取的值是minValue
                // 不用修改minValue
                return minValue
            } else { // minValue更大，min上次取的是上次的值
                val lastValue = minValue
                // 按照stack存放规则 diff = val - minValue
                // 有minValue = val - diff
                return (lastValue - diff).toInt()
            }
        }

        fun pop() {
            minValue = getLastMinValue()
            stack.pop()
        }

        fun top(): Int {
            return (stack.peek() + getLastMinValue()).toInt()
        }

        fun getMin(): Int {
            return minValue
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

    @Test
    fun test2() {
        val minStack = MinStack()
        minStack.push(Int.MIN_VALUE);
        minStack.push(Int.MAX_VALUE);
        assert(minStack.top() == Int.MAX_VALUE)
    }
}