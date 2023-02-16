package com.backinfile;

import com.backinfile.lintCode.TreeNode;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.parse("[10,5,15,3,7,null,18]");
        assert treeNode != null;
        assert treeNode.val == 10;
        assert treeNode.left.val == 5;
        assert treeNode.right.val == 15;
        assert treeNode.left.left.val == 3;
        assert treeNode.left.right.val == 7;
        assert treeNode.left.right.left == null;
        assert treeNode.left.right.right == null;
        assert treeNode.right.left == null;
        assert treeNode.right.right.val == 18;
        assert treeNode.right.right.left == null;
    }
}
