package com.backinfile

import com.alibaba.fastjson.JSON
import com.backinfile.lintCode.TreeNode

class KtUtils {
}


fun <T : Comparable<T>> List<List<T>>.sorted(): List<List<T>> {
    return this.sortedWith(Utils::compareList)
}

fun String.toIntArray(): IntArray {
    return Utils.toIntArray(this)
}

fun String.toIntList(): MutableList<Int> {
    return Utils.toIntArray(this).toMutableList()
}

fun String.toIntArrayArray(): Array<IntArray> {
    return Utils.toIntArrayArray(this)
}

fun String.toCharArrayArray():Array<CharArray> {
    return Utils.toCharArrayArray(this)
}

fun String.toIntListList(): MutableList<MutableList<Int>> {
    return Utils.toIntArrayArray(this).map { it.toMutableList() }.toMutableList()
}

fun String.toTree():TreeNode? {
    return TreeNode.parse(this)
}

fun Array<IntArray>.toIntListList(): MutableList<MutableList<Int>> {
    return this.map { it.toMutableList() }.toMutableList()
}
