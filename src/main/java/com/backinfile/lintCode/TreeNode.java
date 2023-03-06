package com.backinfile.lintCode;

import com.alibaba.fastjson2.JSON;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    public static TreeNode parse(String str) {
        Integer[] array = JSON.parseObject(str, Integer[].class);
        if (array.length == 0) {
            return null;
        }
        TreeNode[] treeNodes = new TreeNode[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            treeNodes[i] = new TreeNode(array[i]);

            int parent = (i - 1) / 2;
            if (parent != i) {
                if (i % 2 == 1) {
                    treeNodes[parent].left = treeNodes[i];
                } else {
                    treeNodes[parent].right = treeNodes[i];
                }
            }
        }
        return treeNodes[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }


    private static final TreeNode Dummy = new TreeNode(-1);

    public List<Integer> toList() {
        var result = new ArrayList<Integer>();
        var queue = new ArrayDeque<TreeNode>();
        queue.add(this);

        while (!queue.isEmpty()) {
            var size = queue.size();
            boolean hasValue = false;
            for (int i = 0; i < size; i++) {
                var node = queue.poll();
                if (node != null && node != Dummy) {
                    result.add(node.val);
                    queue.add(node.left == null ? Dummy : node.left);
                    queue.add(node.right == null ? Dummy : node.right);
                    if (node.left != null || node.right != null) {
                        hasValue = true;
                    }
                } else {
                    result.add(null);
                    queue.add(Dummy);
                    queue.add(Dummy);
                }
            }
            if (!hasValue) {
                break;
            }
        }
        return result;
    }
}
