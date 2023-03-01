package com.backinfile

class KtUtils {
}


fun <T : Comparable<T>> List<List<T>>.sorted(): List<List<T>> {
    return this.sortedWith(Utils::compareList)
}

fun String.toIntArray(): IntArray {
    return Utils.toIntArray(this)
}

fun String.toIntArrayArray(): Array<IntArray> {
    return Utils.toIntArrayArray(this)
}

fun String.toIntListList(): MutableList<MutableList<Int>> {
    return Utils.toIntArrayArray(this).map { it.toMutableList() }.toMutableList()
}

fun Array<IntArray>.toIntListList(): MutableList<MutableList<Int>> {
    return this.map { it.toMutableList() }.toMutableList()
}
