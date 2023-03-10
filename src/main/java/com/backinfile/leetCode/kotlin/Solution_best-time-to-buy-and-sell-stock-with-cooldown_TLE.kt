package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_best-time-to-buy-and-sell-stock-with-cooldown_TLE` {

    fun maxProfit(prices: IntArray): Int {

        var result = 0

        fun dfs(money: Int, index: Int) {
            if (index >= prices.size) {
                result = result.coerceAtLeast(money)
                return
            }

            // 购买当前
            val cur = prices[index]
            for (i in index + 1 until prices.size) { // 找一个高位置的卖出
                if (prices[i] > cur) {
                    dfs(money + prices[i] - cur, i + 2)
                }
            }

            // 不购买当前
            var step = 1
            while (index + step < prices.size && prices[index + step] >= cur) step++
            dfs(money, index + step)
        }

        dfs(0, 0)
        return result
    }

    @Test
    fun test() {
        3 assertEqualTo maxProfit("[1,2,3,0,2]".toIntArray())
        0 assertEqualTo maxProfit("[1]".toIntArray())
    }
}