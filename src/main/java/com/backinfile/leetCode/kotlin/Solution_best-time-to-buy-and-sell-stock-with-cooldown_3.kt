package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import com.backinfile.toIntArray
import org.junit.Test

class `Solution_best-time-to-buy-and-sell-stock-with-cooldown_3` {

    // 优化dp
    fun maxProfit(prices: IntArray): Int {
        var lastNormal = 0
        var lastHas = -prices[0] // 持股
        var lastLock = 0

        for (i in 1..prices.lastIndex) {
            val p = prices[i]
            // 以下当前是指上一次

            var normal = 0
            var has = -p // 持股
            var lock = 0

            // 当前持股状态
            // 不卖出
            has = maxOf(has, lastHas)
            // 卖出
            lock = maxOf(lock, lastHas + p)

            // 当前冷却中
            normal = maxOf(normal, lastLock)

            // 当前未持股
            // 不买
            normal = maxOf(normal, lastNormal)
            // 买
            has = maxOf(has, lastNormal - p)

            lastNormal = normal
            lastHas = has
            lastLock = lock
        }

        return maxOf(lastNormal, lastLock)
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