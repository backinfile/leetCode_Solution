package com.backinfile.leetCode.kotlin

import org.junit.Test
import kotlin.math.min

class `Solution_min-stack_3` {

    class MinStack {
        private var stack = java.util.Stack<Int>()
        private var minValue: Int = Int.MAX_VALUE // 当前最小值

        fun push(`val`: Int) {
            if (`val` <= minValue) {
                stack.add(minValue)
                minValue = `val`
            }
            stack.add(`val`)

        }

        fun pop() {
            val value = stack.pop()
            if (value == minValue) {
                minValue = stack.pop()
            }
        }

        fun top(): Int {
            return stack.peek()
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
    fun test0() {
        val minStack = MinStack()
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        assert(0 == minStack.getMin())
        minStack.pop()
        assert(0 == minStack.getMin())
    }

    @Test
    fun test2() {
        val minStack = MinStack()
        minStack.push(Int.MIN_VALUE);
        minStack.push(Int.MAX_VALUE);
        assert(minStack.top() == Int.MAX_VALUE)
        assert(minStack.getMin() == Int.MIN_VALUE)
    }

    @Test
    fun test3() {
        val minStack = MinStack()
        minStack.push(Int.MAX_VALUE);
        minStack.push(Int.MIN_VALUE);
        assert(minStack.top() == Int.MIN_VALUE)
        assert(minStack.getMin() == Int.MIN_VALUE)
    }

    // ["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"] [[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
}