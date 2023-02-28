package com.backinfile.leetCode.kotlin

import com.backinfile.Utils
import org.junit.Test

class `Solution_group-anagrams` {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = hashMapOf<Int, ArrayList<String>>()
        for (str in strs) {
            val hash = hash(str)
            if (hash !in map) {
                map[hash] = ArrayList()
            }
            map[hash]!!.add(str)
        }
        return map.values.toList()
    }

    private fun hash(str: String): Int {
        var hash = 0
        hash += str.length
        val list = str.codePoints().toArray()
        list.sort()
        for (ch in list) {
            hash = (hash * 33) + ch
        }
        return hash
    }


    @Test
    fun test() {
        assert(
            testFunc(
                "[\"eat\", \"tea\", \"tan\", \"ate\", \"nat\", \"bat\"]",
                "[[\"bat\"],[\"nat\",\"tan\"],[\"ate\",\"eat\",\"tea\"]]"
            )
        )
        assert(testFunc("[\"\"]", "[[\"\"]]"))
        assert(testFunc("[\"a\"]", "[[\"a\"]]"))
    }

    private fun testFunc(inputStr: String, answerStr: String): Boolean {
        val output =
            groupAnagrams(Utils.toStrArray(inputStr)).map { it.sorted() }.sortedWith(Utils::compareList).toList()
        val answer = Utils.toStrArrayArray(answerStr).map { it.sorted() }.sortedWith(Utils::compareList).toList()
        println(output)
        return output == answer
    }
}