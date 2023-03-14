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
        while (!result.isEmpty() && result.get(result.size() - 1) == null) result.remove(result.size() - 1);
        return result;
    }
}
