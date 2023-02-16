package com.backinfile.lintCode;

import com.alibaba.fastjson2.JSON;
import org.junit.Test;

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

}
