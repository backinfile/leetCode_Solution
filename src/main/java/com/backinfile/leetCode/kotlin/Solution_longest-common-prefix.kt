package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_longest-common-prefix` {
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) {
            return ""
        }
        val minLength = strs.minOf { it.length }
        for (i in 0 until minLength) {
            val ch = strs[0][i]
            if (strs.any { it[i] != ch }) {
                return strs[0].substring(0, i)
            }
        }
        return strs[0].substring(0, minLength)
    }

    @Test
    fun test() {
        assert(longestCommonPrefix(Utils.toStrArray("[\"flower\",\"flow\",\"flight\"]")) == "fl")
        assert(longestCommonPrefix(Utils.toStrArray("[\"dog\",\"racecar\",\"car\"]")) == "")
        assert(longestCommonPrefix(Utils.toStrArray("[\"a\"]")) == "a")
        assert(longestCommonPrefix(Utils.toStrArray("[\"ab\", \"a\"]")) == "a")
    }
}