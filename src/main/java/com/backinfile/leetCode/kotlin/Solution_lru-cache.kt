package com.backinfile.leetCode.kotlin

import org.junit.Test
import java.util.LinkedHashMap

class LRUCache(val capacity: Int) {
    private val list = DoubleList()
    private val map = HashMap<Int, DoubleList.Node>()
    fun get(key: Int): Int {
        val node = map[key] ?: return -1

        if (list.isFirst(node)) {
            return node.value
        }
        list.removeFromList(node)
        list.addNodeToFirst(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        var node = map[key]
        if (node == null) {
            node = DoubleList.Node(key, value)
            map[key] = node

            list.addNodeToFirst(node)

            // 数目超了，删除最后一个节点
            if (map.size > capacity) {
                list.removeLastNode()?.let { map.remove(it.key) }
            }
        } else {
            node.value = value
            list.moveNodeToFirst(node)
        }
    }
}

class Map {


}

// 双向链表
class DoubleList {
    // 双向链表
    private val head: Node = Node(-1, -1)
    private val tail: Node = Node(-1, -1)

    init {
        head.next = tail
        tail.before = head
    }


    class Node(val key: Int, var value: Int) {
        var next: Node? = null
        var before: Node? = null
    }

    fun isFirst(node: Node?): Boolean {
        return node == head
    }

    fun removeFromList(node: Node) {
        node.before!!.next = node.next
        node.next!!.before = node.before
    }

    // 将节点插入到最前
    fun addNodeToFirst(node: Node) {
        node.next = head.next
        node.before = head
        head.next!!.before = node
        head.next = node
    }

    fun moveNodeToFirst(node: Node) {
        if (node == head.next) {
            return
        }
        removeFromList(node)
        addNodeToFirst(node)
    }

    // 移除最后一个节点并返回
    fun removeLastNode(): Node? {
        if (tail.before == head) {
            return null
        }
        val remove = tail.before
        remove!!.before!!.next = tail
        tail.before = remove.before
        return remove
    }
}

class LRUCache_EASY_WAY(capacity: Int) {
    val map = object : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
            return size > capacity
        }
    }

    fun get(key: Int): Int {
        return map[key] ?: -1
    }

    fun put(key: Int, value: Int) {
        map[key] = value
    }
}

class `Solution_lru-cache` {

    @Test
    fun test() {
        val cache = LRUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        assert(cache.get(1) == 1)
        cache.put(3, 3)
        assert(cache.get(2) == -1)
        cache.put(4, 4)
        assert(cache.get(1) == -1)
        assert(cache.get(3) == 3)
        assert(cache.get(4) == 4)
    }


    @Test
    fun test2() {
        val cache = LRUCache(2)
        cache.put(1, 0)
        cache.put(2, 2)
        assert(cache.get(1) == 0)
        cache.put(3, 3)
        assert(cache.get(2) == -1)
        cache.put(4, 4)
        assert(cache.get(1) == -1)
        assert(cache.get(3) == 3)
        assert(cache.get(4) == 4)
    }
}
