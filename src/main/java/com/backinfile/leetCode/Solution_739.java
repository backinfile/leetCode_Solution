package com.backinfile.leetCode;

import com.backinfile.Utils;
import org.junit.Test;

import java.util.Arrays;

public class Solution_739 {

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 0) {
            return new int[]{};
        }
        int[] result = new int[temperatures.length];
        int[] stack = new int[temperatures.length];
        int stackTop = -1;


        for (int i = 0; i < temperatures.length; i++) {
            int curValue = temperatures[i];
            if (stackTop > -1) {
                while (curValue > temperatures[stack[stackTop]]) {
                    int lastIndex = stack[stackTop];
                    result[lastIndex] = i - lastIndex;
                    if (--stackTop == -1) {
                        break;
                    }
                }
            }
            stack[++stackTop] = i;
        }
        for (int i = 0; i < stackTop; i++) {
            result[stack[i + 1]] = 0;
        }
        return result;
    }


    @Test
    public void test() {
        assert Arrays.equals(dailyTemperatures(Utils.toIntArray("[73,74,75,71,69,72,76,73]")),
                Utils.toIntArray("[1,1,4,2,1,1,0,0]"));
        assert Arrays.equals(dailyTemperatures(Utils.toIntArray("[30,40,50,60]")),
                Utils.toIntArray("[1,1,1,0]"));
        assert Arrays.equals(dailyTemperatures(Utils.toIntArray("[30,60,90]")),
                Utils.toIntArray("[1,1,0]"));
    }
}
