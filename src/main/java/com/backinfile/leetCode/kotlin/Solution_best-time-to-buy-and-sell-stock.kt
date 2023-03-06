package com.backinfile.leetCode.kotlin

import com.backinfile.toIntArray
import org.junit.Test
import kotlin.math.max

class `Solution_best-time-to-buy-and-sell-stock` {
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) {
            return 0
        }
        var result = 0
        var min = prices[0]
        for (i in 1..prices.lastIndex) {
            result = result.coerceAtLeast(prices[i] - min)
            min = min.coerceAtMost(prices[i])
        }
        return result
    }

    @Test
    fun test() {
        assert(5 == maxProfit("[7,1,5,3,6,4]".toIntArray()))
        assert(0 == maxProfit("[7,6,4,3,1]".toIntArray()))
    }
}