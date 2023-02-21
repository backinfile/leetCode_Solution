package com.backinfile.lintCode;

import org.junit.Test;

import java.util.Arrays;

public class Solution_1700 {
    /**
     * @param s:
     * @return: nothing
     */
    public int[] diStringMatch(String s) {
        int min = 0;
        int max = s.length();

        int[] result = new int[s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case 'I':
                    result[i] = min;
                    min++;
                    break;
                case 'D':
                    result[i] = max;
                    max--;
                    break;
            }
        }
        result[s.length()] = min;
        return result;
    }


    @Test
    public void test() {
        assert Arrays.equals(diStringMatch("IDID"), new int[]{0, 4, 1, 3, 2});
        assert Arrays.equals(diStringMatch("III"), new int[]{0, 1, 2, 3});
    }
}
