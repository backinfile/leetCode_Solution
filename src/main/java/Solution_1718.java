import java.util.*;

public class Solution_1718 {
    public int[] constructDistancedSequence(int n) {
        int[] result = new int[n * 2 - 1];
        HashMap<Integer, Integer> eleIndexMap = new HashMap<>();
        dps(n, 0, result, eleIndexMap);
        return result;
    }

    private boolean dps(int n, int index, int[] result, Map<Integer, Integer> eleIndexMap) {
        if (index >= result.length) {
            return true;
        }
        if (result[index] > 0) {
            return dps(n, index + 1, result, eleIndexMap);
        }

        for (int curValue = n; curValue >= 1; curValue--) {
            if (eleIndexMap.containsKey(curValue)) {
                continue;
            }

            if (curValue != 1) {
                int nextIndex = curValue + index;
                if (nextIndex >= result.length || result[nextIndex] > 0) {
                    continue;
                }
            }

            result[index] = curValue;
            if (curValue != 1) {
                result[index + curValue] = curValue;
            }
//            System.out.printf("%s put %d at %d %s%n", makeRepeat(" ", index), curValue, index, makeRepeat(" ", 2 * n - index) + getArrayString(result, index + 1));
            eleIndexMap.put(curValue, 1);
            if (dps(n, index + 1, result, eleIndexMap)) {
                return true;
            }
            result[index] = 0;
            if (curValue != 1) {
                result[index + curValue] = 0;
            }
            eleIndexMap.remove(curValue);
        }
        return false;
    }

    private String makeRepeat(String str, int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(str);
        }
        return result.toString();
    }

    private String getArrayString(int[] arr, int n) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for (int i = 0; i < n; i++) {
            sj.add(String.valueOf(arr[i]));
        }
        return sj.toString();
    }

    public static void main(String[] args) {
        // [6,4,2,5,2,4,6,3,5,1,3]
        System.out.println(Arrays.toString(new Solution_1718().constructDistancedSequence(6)));
    }
}
