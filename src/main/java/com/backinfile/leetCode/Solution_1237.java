package com.backinfile.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_1237 {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 1000;
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        for(int x = MIN_VALUE; x <= MAX_VALUE; x++) {
            int left = MIN_VALUE;
            int right = MAX_VALUE;
            int mid = (left + right) / 2;
            if (customfunction.f(x, left) == z) {
                result.add(Arrays.asList(x, left));
                continue;
            }
            if (customfunction.f(x, right) == z) {
                result.add(Arrays.asList(x, right));
                continue;
            }
            while (left <= right) {
                int value = customfunction.f(x, mid);
                if (value < z) {
                    left = mid + 1;
                    mid = (left + right) / 2;
                } else if (value > z) {
                    right = mid - 1;
                    mid = (left + right) / 2;
                } else {
                    result.add(Arrays.asList(x, mid));
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_1237().findSolution(new CustomFunction(), 5));
    }
}
