package com.backinfile.lintCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_962 {
    /**
     * @param s: a string only contains `a-f`
     * @return: The longest length that satisfies the condition
     */
    public int conditionString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return LIS(s, "ace") + LIS(s, "bdf");
    }

    private int LIS(String s, String take) {
        int[] takes = take.codePoints().toArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int cur = s.codePointAt(i);
            if (!contains(takes, cur)) {
                continue;
            }

            if (list.isEmpty() || cur >= list.get(list.size() - 1)) {
                list.add(cur);
            } else {
                list.set(upperBoundBiSearch(list, cur), cur);
            }
        }
//        System.out.println(list);
        return list.size();
    }

    private boolean contains(int[] array, int value) {
        for (int j : array) {
            if (j == value) {
                return true;
            }
        }
        return false;
    }

    private int upperBoundBiSearch(List<Integer> list, int value) {
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            int midValue = list.get(mid);
            if (midValue > value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    @Test
    public void test() {
        assert conditionString("abcdef") == 6;
        assert conditionString("fedcba") == 2;
        assert conditionString("abacfecbbd") == 8;
    }

    @Test
    public void testBiSearch() {
        assert upperBoundBiSearch(Arrays.asList(1, 3, 5), 1) == 1;
        assert upperBoundBiSearch(Arrays.asList(1, 3, 5), 3) == 2;
        assert upperBoundBiSearch(Arrays.asList(1, 3, 5), 5) == 2;
        assert upperBoundBiSearch(Arrays.asList(1, 3, 5), 4) == 2;
        assert upperBoundBiSearch(Arrays.asList(1, 3, 5), 6) == 2;
        assert upperBoundBiSearch(Arrays.asList(1), 6) == 0;
        assert upperBoundBiSearch(Arrays.asList(1), 0) == 0;
    }
}
