package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_best-time-to-buy-and-sell-stock-with-cooldown_2` {

    // 优化dp
    fun maxProfit(prices: IntArray): Int {
        val dp = IntArray(prices.size) // 无股
        val dpLock = IntArray(prices.size) // 冷却中
        val dpHas = IntArray(prices.size) // 持股

        dp[0] = 0
        dpHas[0] = -prices[0]
        dpLock[0] = 0

        for (i in 1..prices.lastIndex) {
            val p = prices[i]

            var normal = 0
            var has = -p // 持股
            var lock = 0

            // 以下当前是指i-1

            // 当前持股状态
            // 不卖出
            has = maxOf(has, dpHas[i - 1])
            // 卖出
            lock = maxOf(lock, dpHas[i - 1] + p)

            // 当前冷却中
            normal = maxOf(normal, dpLock[i - 1])

            // 当前未持股
            // 不买
            normal = maxOf(normal, dp[i - 1])
            // 买
            has = maxOf(has, dp[i - 1] - p)

            dp[i] = normal
            dpHas[i] = has
            dpLock[i] = lock
        }

        return maxOf(dp[prices.lastIndex], dpLock[prices.lastIndex])
    }

    @Test
    fun test() {
        6 assertEqualTo maxProfit("[6,1,3,2,4,7]".toIntArray())
        3 assertEqualTo maxProfit("[1,2,4]".toIntArray())
        1 assertEqualTo maxProfit("[1,2]".toIntArray())
        3 assertEqualTo maxProfit("[1,2,3,0,2]".toIntArray())
        0 assertEqualTo maxProfit("[1]".toIntArray())
    }
}