package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_letter-combinations-of-a-phone-number` {
    fun letterCombinations(digits: String): List<String> {
        if (digits == "") {
            return listOf()
        }

        val result = arrayListOf<String>()
        val array = CharArray(digits.length)
        addChar(digits, 0, array, result)
        return result
    }

    private fun addChar(digits: String, cur: Int, array: CharArray, result: MutableList<String>) {
        if (cur >= digits.length) {
            result.add(array.joinToString(""))
            return
        }
        BOARD[digits[cur] - '0'].forEach {
            array[cur] = it
            addChar(digits, cur + 1, array, result);
        }
    }

    companion object {
        val BOARD = arrayOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
    }

    @Test
    fun test() {
        assert(equals(letterCombinations("23"), "[\"ad\",\"ae\",\"af\",\"bd\",\"be\",\"bf\",\"cd\",\"ce\",\"cf\"]"));
        assert(equals(letterCombinations(""), "[]"));
        assert(equals(letterCombinations("2"), "[\"a\",\"b\",\"c\"]"));
    }

    private fun equals(output: List<String>, answer: String): Boolean {
        return output.toSet() == Utils.toStrArray(answer).toSet()
    }
}