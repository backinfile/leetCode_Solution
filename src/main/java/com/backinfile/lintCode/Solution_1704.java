package com.backinfile.lintCode;

import com.backinfile.KtUtilsKt;
import org.junit.Test;

public class Solution_1704 {
    /**
     * @param root: the root node
     * @param l:    an integer
     * @param r:    an integer
     * @return: the sum
     */
    public int rangeSumBST(TreeNode root, int l, int r) {
        if (root == null) {
            return 0;
        }
        if (root.val >= r) {
            return rangeSumBST(root.left, l, r) + ((root.val == r) ? root.val : 0);
        }
        if (root.val <= l) {
            return rangeSumBST(root.right, l, r) + ((root.val == l) ? root.val : 0);
        }
        return root.val + rangeSumBST(root.left, l, r) + rangeSumBST(root.right, l, r);
    }


    @Test
    public void test() {
        assert rangeSumBST(KtUtilsKt.toTree("[10,5,15,3,7,null,18]"), 7, 15) == 32;
        assert rangeSumBST(KtUtilsKt.toTree("[10,5,15,3,7,13,18,1,null,6]"), 6, 10) == 23;
    }
}
