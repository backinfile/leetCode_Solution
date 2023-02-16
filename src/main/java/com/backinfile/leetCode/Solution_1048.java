package com.backinfile.leetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution_1048 {
    public int longestStrChain(String[] words) {
        int[] result = new int[words.length];
        Arrays.fill(result, 1);
        Arrays.sort(words, Comparator.comparingInt(String::length));
//        System.out.println(Arrays.toString(words));
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (isAddString(words, i, j)) {
                    result[j] = Math.max(result[j], result[i] + 1);
//                    System.out.println(Arrays.toString(result));
                }
            }
        }
        return IntStream.of(result).max().getAsInt();
    }

    private static boolean isAddString(String[] words, int cur, int target) {
        String curString = words[cur];
        String targetString = words[target];
        int length = curString.length();
        if (length + 1 != targetString.length()) {
            return false;
        }

        int firstDiffIndex = getFirstDiffIndex(curString, targetString);
        if (firstDiffIndex == curString.length()) {
            return true;
        }
        return equals(curString, firstDiffIndex, targetString, firstDiffIndex + 1);
    }

    private static int getFirstDiffIndex(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return i;
            }
        }
        return a.length();
    }

    private static boolean equals(String a, int startA, String b, int startB) {
        for (int i = 0; i < a.length() - startA; i++) {
            if (a.charAt(startA + i) != b.charAt(startB + i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // ["a","aa","aab","aabb","bbaac"]
        // 4
        //["qyssedya","pabouk","mjwdrbqwp","vylodpmwp","nfyqeowa","pu","paboukc","qssedya","lopmw","nfyqowa","vlodpmw","mwdrqwp","opmw","qsda","neo","qyssedhyac","pmw","lodpmw","mjwdrqwp","eo","nfqwa","pabuk","nfyqwa","qssdya","qsdya","qyssedhya","pabu","nqwa","pabqoukc","pbu","mw","vlodpmwp","x","xr"]
        // 8
        System.out.println(new Solution_1048().longestStrChain(new String[]{"a", "aa", "aab", "aabb", "bbaac"}));
//        System.out.println(new com.backinfile.leetCode.Solution_1048().longestStrChain(new String[]{"qyssedya","pabouk","mjwdrbqwp","vylodpmwp","nfyqeowa","pu","paboukc","qssedya","lopmw","nfyqowa","vlodpmw","mwdrqwp","opmw","qsda","neo","qyssedhyac","pmw","lodpmw","mjwdrqwp","eo","nfqwa","pabuk","nfyqwa","qssdya","qsdya","qyssedhya","pabu","nqwa","pabqoukc","pbu","mw","vlodpmwp","x","xr"}));
    }
}
