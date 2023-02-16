package com.backinfile;

import com.backinfile.lintCode.Solution_518;
import com.backinfile.lintCode.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UtilsTest {

    @Test
    public void tree() {
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

    @Test
    public void heap() {
        Solution_518.Heap heap = new Solution_518.Heap(10);
        Arrays.asList(1, 4, 6, 3, 1, 3, 77, 1, 4, 0).forEach(heap::add);
        assert heap.poll() == 0;
        assert heap.poll() == 1;
        assert heap.poll() == 1;
        assert heap.poll() == 1;
        assert heap.poll() == 3;
        assert heap.poll() == 3;
        assert heap.poll() == 4;
        assert heap.poll() == 4;
        assert heap.poll() == 6;
        assert heap.poll() == 77;
        assert heap.isEmpty();
    }

    @Test
    public void heap2() {
        Solution_518.Heap heap = new Solution_518.Heap(10);
        Arrays.asList(1, 4, 6, 3, 3, 77, 4, 0).forEach(heap::add);
        assert heap.poll() == 0;
        assert heap.poll() == 1;
        assert heap.poll() == 3;
        heap.add(1);
        assert heap.poll() == 1;
        assert heap.poll() == 3;
        assert heap.poll() == 4;
        assert heap.poll() == 4;
        assert heap.poll() == 6;
        assert heap.poll() == 77;
        assert heap.isEmpty();
    }
}
