package com.backinfile.leetCode.kotlin

import com.backinfile.assertEqualTo
import org.junit.Test

class `Solution_palindromic-substrings_2` {
    fun countSubstrings(_s: String): Int {
        // 在字符之间插入#，使得所有回文串都是奇数长度
        // 同时在边界上添加了特殊符号，这样就不用特殊处理边界了
        val s = _s.toCharArray().joinToString("#", "^#", "#$")

        val dp = IntArray(s.length) // dp[i]表示s[i]处最大回文半径

        var pMaxCenterIndex = 0 // 当前最长回文串的坐标

        var result = 0
        for (i in 1 until s.lastIndex) {
            // 当前最长回文串的最右侧坐标
            val pMaxRightIndex = pMaxCenterIndex + dp[pMaxCenterIndex] - 1

            // f是当前所求的回文半径 回文串表示为s[i-f+1..i+f-1]
            // 如果当前坐标在当前最大回文串范围内，找到对称位置2 * pMaxCenterIndex - i
            // f不低于对称位置的回文半径 (不能超出当前最大回文串)
            var f = if (i <= pMaxRightIndex) minOf(dp[2 * pMaxCenterIndex - i], pMaxRightIndex - i + 1) else 1

            while (s[i - f] == s[i + f]) f++ // 只需按奇数回文串处理即可，也不用处理边界
            dp[i] = f

            // 更新最长回文串
            if (i + f - 1 > pMaxRightIndex) {
                pMaxCenterIndex = i
            }

            // 统计回文串个数
            result += f / 2
        }
        return result
    }

    @Test
    fun test() {
        3 assertEqualTo countSubstrings("abc")
        6 assertEqualTo countSubstrings("aaa")
    }
}