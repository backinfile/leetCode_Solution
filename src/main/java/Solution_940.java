import java.util.Arrays;

public class Solution_940 {
    private static final int MOD = 1000000007;

    public int distinctSubseqII(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.codePointAt(i) - 'a'] = 1 + sum(count);
        }
        return sum(count);
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int a : arr) {
            sum = (a + sum) % MOD;
        }
        return sum;
    }
}
