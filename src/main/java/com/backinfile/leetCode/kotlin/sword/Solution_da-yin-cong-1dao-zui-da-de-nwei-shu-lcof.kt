package com.backinfile.leetCode.kotlin.sword

class `Solution_da-yin-cong-1dao-zui-da-de-nwei-shu-lcof` {
    fun printNumbers(n: Int): IntArray {
        val max = (1..n).map { 9 }.reduce { acc, v -> acc * 10 + v }
        return (1..max).toList().toIntArray()
    }
}