package com.backinfile.leetCode.kotlin

import com.backinfile.toCharArrayArray
import org.junit.Test

class `Solution_maximal-rectangle_2` {
    fun maximalRectangle(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) {
            return 0
        }

        var maxArea = 0

        val width = matrix.size
        val height = matrix[0].size

        val stack = Stack(height)
        val heights = IntArray(height)


        fun Int.height(): Int {
            return heights[this]
        }

        // 弹出栈中不低于height的元素，stack为单调递增栈
        fun popLarger(height: Int, curIndex: Int) {
            while (!stack.isEmpty() && stack.peek().height() >= height) {
                // 被弹出栈的高度（要立即计算当前可能的最大值，不然弹出之后就没有了)
                val h = stack.pop().height()
                // stack.peek()是上一个高度的最后一个位置 stack.peek()+1是当前高度的第一个位置
                // curIndex-1是当前高度的最后一个位置
                // w=last-first+1 = curIndex-1 + stack.peek()+1 -1
                val w = curIndex - 1 - if (stack.isEmpty()) -1 else stack.peek()
                maxArea = maxArea.coerceAtLeast(h * w)
            }
        }


        for (loop in 0 until width) {
            stack.clear()


            for (i in 0 until height) {
                val curHeight = if (matrix[loop][i] == '0') 0 else heights[i] + 1
                heights[i] = curHeight
                popLarger(curHeight, i)
                stack.push(i)
            }
            popLarger(0, height) // 最后把栈中剩余的元素全部弹出
        }
        return maxArea
    }

    class Stack(capability: Int) {
        val array = IntArray(capability)
        var size = 0
            private set

        fun push(v: Int) {
            array[size++] = v
        }

        fun pop(): Int {
            return array[--size]
        }

        fun peek(): Int {
            return array[size - 1]
        }

        fun isEmpty(): Boolean {
            return size == 0
        }

        fun clear() {
            size = 0
        }
    }

    @Test
    fun test() {
        assert(6 == maximalRectangle("[[\"1\",\"0\",\"1\",\"0\",\"0\"],[\"1\",\"0\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"0\",\"0\",\"1\",\"0\"]]".toCharArrayArray()))
        assert(0 == maximalRectangle("[]".toCharArrayArray()))
        assert(0 == maximalRectangle("[[\"0\"]]".toCharArrayArray()))
        assert(1 == maximalRectangle("[[\"1\"]]".toCharArrayArray()))
        assert(0 == maximalRectangle("[[\"0\",\"0\"]]".toCharArrayArray()))
        assert(1 == maximalRectangle("[[\"1\",\"0\"]]".toCharArrayArray()))
        assert(2 == maximalRectangle("[[\"1\",\"1\"]]".toCharArrayArray()))
        assert(3 == maximalRectangle("[[\"1\",\"1\",\"1\"]]".toCharArrayArray()))
        assert(1 == maximalRectangle("[[\"1\",\"0\",\"1\"]]".toCharArrayArray()))
    }
}