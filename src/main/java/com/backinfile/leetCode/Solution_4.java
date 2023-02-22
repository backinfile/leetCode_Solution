package com.backinfile.leetCode;

import com.backinfile.Utils;
import org.junit.Test;

public class Solution_4 {

    // O(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }

        int maxLength = nums1.length + nums2.length;

        boolean odd = maxLength % 2 == 1;
        int findIndex = maxLength / 2;
        int findIndex2 = odd ? maxLength / 2 : maxLength / 2 - 1;
        int[] result = new int[2];

        int p1 = 0;
        int p2 = 0;

        boolean found = false;
        boolean found2 = false;

        int totalP = 0;
        while (!found || !found2) {
            // 这个地方找下一个坐标的时候可以尽可能跳转到后面， 这样就是O(log(m+n))
            int select = 0;
            if (p2 >= nums2.length) {
                select = 1;
            } else if (p1 >= nums1.length) {
                select = 2;
            } else {
                select = (nums1[p1] > nums2[p2]) ? 2 : 1;
            }

            if (select == 1) {
                if (totalP == findIndex) {
                    result[0] = nums1[p1];
                    found = true;
                }
                if (totalP == findIndex2) {
                    result[1] = nums1[p1];
                    found2 = true;
                }
                totalP++;
                p1++;
            } else {
                if (totalP == findIndex) {
                    result[0] = nums2[p2];
                    found = true;
                }
                if (totalP == findIndex2) {
                    result[1] = nums2[p2];
                    found2 = true;
                }
                totalP++;
                p2++;
            }
        }
        return (result[0] + result[1]) / 2f;
    }


    @Test
    public void test() {
        assert Utils.equal(2.00000d, findMedianSortedArrays(Utils.toIntArray("[1,3]"), Utils.toIntArray("[2]")));
        assert Utils.equal(2.50000d, findMedianSortedArrays(Utils.toIntArray("[1,2]"), Utils.toIntArray("[3,4]")));
    }
}
