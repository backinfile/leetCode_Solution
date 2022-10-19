import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_904 {
    public int totalFruit(int[] fruits) {
        if (fruits.length == 0) {
            return 0;
        }
        int maxValue = 1;

        Map<Integer, Integer> cntMap = new HashMap<>();
        int left = 0;
        for (int right = 0; right < fruits.length && left < fruits.length; right++) {
            int oldValue = cntMap.getOrDefault(fruits[right], 0);
            cntMap.put(fruits[right], oldValue + 1);

            while (cntMap.size() > 2) {
                int leftValue = cntMap.getOrDefault(fruits[left], 0);
                if (leftValue == 1) {
                    cntMap.remove(fruits[left]);
                } else {
                    cntMap.put(fruits[left], leftValue - 1);
                }
                left++;
            }
            maxValue = Math.max(maxValue, right - left + 1);
        }

        return maxValue;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_904().totalFruit(new int[]{1, 2, 3, 2, 2}));
    }
}
