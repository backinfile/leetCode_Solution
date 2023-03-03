package com.backinfile.leetCode.kotlin

import com.backinfile.toCharArrayArray
import org.junit.Test

class `Solution_maximal-rectangle` {
    fun maximalRectangle(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) {
            return 0
        }

        var maxArea = 0

        val width = matrix.size
        val height = matrix[0].size

        val stack = Stack(height)

        for (j in 0 until height) {
            var count = 0
            for (i in 0 until width) {
                count = if (matrix[i][j] == '1') count + 1 else 0
                matrix[i][j] = count.toChar()
            }
        }


        for (loop in 0 until width) {
            stack.clear()
            fun Int.height(): Int {
                return matrix[loop][this].toInt()
            }

            for (i in 0 until height) {
                val curHeight = i.height()

                while (!stack.isEmpty() && stack.peek().height() >= curHeight) {
                    val h = stack.pop().height()
                    val w = i - 1 - if (stack.isEmpty()) -1 else stack.peek()
                    maxArea = maxArea.coerceAtLeast(h * w)
//                    println("$loop,$i max:${w * h} w:$w h:$h")
                }
                stack.push(i)
            }

            while (!stack.isEmpty() && stack.peek().height() > 0) {
                val h = stack.pop().height()
                val w = height - 1 - if (stack.isEmpty()) -1 else stack.peek()
                maxArea = maxArea.coerceAtLeast(h * w)
//                println("$loop,$height max:${w * h} w:$w h:$h")
            }
        }

//        println(maxArea)
        return maxArea
    }

    class Stack(capability: Int) {
        val array = IntArray(capability)
        var size = 0
            private set

        constructor(capability: Int, init: Int) : this(capability) {
            push(init)
        }

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