package com.backinfile.lintCode;

import com.backinfile.Utils;
import org.junit.Test;

import java.util.Arrays;

public class Solution_109 {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        if (triangle.length == 0) {
            return 0;
        }

        int length = triangle[triangle.length - 1].length;

        int[] result1 = new int[length];
        int[] result2 = new int[length];

        for (int i = 0; i < triangle[0].length; i++) {
            result1[i] = triangle[0][i];
        }

        for (int layer = 1; layer < triangle.length; layer++) {
            int lastLayerLength = triangle[layer - 1].length;
            for (int i = 0; i < triangle[layer].length; i++) {
                if (i == 0) {
                    result2[i] = result1[i] + triangle[layer][i];
                } else if (i < lastLayerLength) {
                    result2[i] = Math.min(result1[i - 1], result1[i]) + triangle[layer][i];
                } else {
                    result2[i] = result1[i - 1] + triangle[layer][i];
                }
            }

            {
                int[] tmp = result1;
                result1 = result2;
                result2 = tmp;
            }
        }
        int result = Arrays.stream(result1).limit(length).min().getAsInt();
//        System.out.printf("result = %d%n", result);
        return result;
    }


    @Test
    public void test() {
        assert 11 == minimumTotal(Utils.toIntIntArray("[\n" +
                "     [2],\n" +
                "    [3,4],\n" +
                "   [6,5,7],\n" +
                "  [4,1,8,3]\n" +
                "]"));
        assert 12 == minimumTotal(Utils.toIntIntArray("[\n" +
                "     [2],\n" +
                "    [3,2],\n" +
                "   [6,5,7],\n" +
                "  [4,4,8,1]\n" +
                "]"));
    }
}
