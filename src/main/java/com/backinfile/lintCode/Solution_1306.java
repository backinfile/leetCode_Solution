package com.backinfile.lintCode;

import com.backinfile.leetCode.Utils;
import org.junit.Test;

public class Solution_1306 {

    /**
     * @param arr: the distance between any two cities
     * @return: the minimum distance Alice needs to walk to complete the travel plan
     */
    public int travelPlanII(int[][] arr) {
        int dp = forDp(arr);
//        System.out.println(dp);
        return dp;
    }

    private int forDp(int[][] arr) {
        int[][] dp = new int[1 << arr.length][arr.length];

        // init
        for (int i = 1; i < arr.length; i++) {
            dp[1 << i][i] = arr[i][0];
        }

        // dp[foot | (1<<before)][before] = min of select {dp[foot][select] + arr[before][select]}
        for (int foot = 0; foot < dp.length; foot++) {
            for (int select = 1; select < arr.length; select++) { // the last select number
                int curDistance = dp[foot][select];
                if (curDistance == 0) {
                    continue;
                }
                for (int before = 1; before < arr.length; before++) { // the before select number
                    if ((foot & (1 << before)) != 0) { // used
                        continue;
                    }

                    int distance = curDistance + arr[before][select];
                    int beforeDistance = dp[foot | (1 << before)][before];
                    if (beforeDistance == 0 || distance < beforeDistance) {
                        dp[foot | (1 << before)][before] = distance;
//                        System.out.printf("update dp[%d][%d] = %d %n", foot | (1 << before), before, distance);
                    }
                }
            }
        }

        int minValue = -1;
        int fullMask = (1 << arr.length) - 1 - 1;
        for (int foot = 0; foot < dp.length; foot++) {
            if (foot == fullMask) {
                for (int select = 1; select < arr.length; select++) {
                    int curValue = dp[foot][select] + arr[0][select];
                    if (curValue > 0) {
                        if (minValue < 0 || curValue < minValue) {
                            minValue = curValue;
                        }
                    }
                }
            }
        }
        return minValue;
    }

    private int dp(int[][] arr, int foot, int index, int curDistance, int lastIndex) {
        // 选择一个没有选择过的，进行dp

        // 最后一次 一定返回0
        int length = arr.length;
        if (index == length - 1) {
            return curDistance + arr[lastIndex][0];
        }

        int minDistance = -1;

        for (int i = 1; i < length; i++) {
            if ((foot & (1 << i)) != 0) {
                continue;
            }
//            System.out.printf("enter %d in %d%n", i, index);
            int result = dp(arr, foot | (i << i), index + 1, curDistance + arr[lastIndex][i], i);
            if (minDistance < 0 || result < minDistance) {
                minDistance = result;
            }
        }

        if (minDistance < 0) {
            throw new RuntimeException("");
        }
        return minDistance;
    }


    @Test
    public void test() {
        assert 4 == travelPlanII(Utils.toIntIntArray("[[0,1,2],[1,0,2],[2,1,0]]"));
        assert 11 == travelPlanII(Utils.toIntIntArray("[[0,10000,2],[5,0,10000],[10000,4,0]]"));
        assert 2447 == travelPlanII(Utils.toIntIntArray("[[0,663,497,549,957,479,988,207,333,882,574,751,144,492,245],[646,0,576,367,236,85,10000,136,10000,711,444,774,10000,10000,43],[10000,560,0,161,697,97,167,10000,10000,251,679,258,339,481,15],[715,481,249,0,10000,504,620,657,685,301,10000,703,59,450,228],[643,755,949,514,0,577,878,10000,327,216,779,882,861,269,679],[388,947,307,10000,842,0,837,595,527,179,10000,161,416,217,370],[716,698,786,669,682,519,0,347,991,337,10000,325,447,764,417],[721,625,629,10000,940,804,74,0,904,121,328,10000,491,774,10000],[841,637,336,525,869,28,978,802,0,677,10000,82,404,46,968],[609,359,733,10000,538,62,123,218,726,0,905,476,10000,122,470],[2,879,877,314,899,96,953,10000,567,860,0,156,772,10000,742],[938,128,290,543,262,352,52,273,506,803,992,0,794,818,10],[587,548,542,10000,805,849,271,75,560,97,813,110,0,183,237],[10000,624,440,10000,763,252,347,581,639,336,732,789,208,0,9],[440,529,210,732,318,10000,10000,346,919,10000,382,383,829,10000,0]]"));
    }
}
