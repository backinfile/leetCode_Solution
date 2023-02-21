package com.backinfile.leetCode;

import org.junit.Test;

import java.util.Arrays;

public class Solution_3 {
    public int lengthOfLongestSubstring(String s) {
        int[] indexes = new int[128];
        Arrays.fill(indexes, -1);

        int max = 0;
        int len = 0;
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int lastIndex = indexes[ch];
            if (lastIndex == -1 || lastIndex < start) {
                len++;
            } else {
                max = Math.max(max, len);
                len = i - lastIndex;
                start = lastIndex;
            }
            indexes[ch] = i;
        }
//        System.out.println(Math.max(len, max));
        return Math.max(len, max);
    }

    @Test
    public void test() {
//        assert 3 == lengthOfLongestSubstring("abcabcbb");
//        assert 1 == lengthOfLongestSubstring("bbbbb");
//        assert 3 == lengthOfLongestSubstring("pwwkew");
        assert 2 == lengthOfLongestSubstring("cdd");
    }
}
