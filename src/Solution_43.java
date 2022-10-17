import java.util.Arrays;
import java.util.Map;

public class Solution_43 {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] result = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                result[i + j] += (num1.charAt(num1.length() - i - 1) - '0') * (num2.charAt(num2.length() - j - 1) - '0');
            }
        }
        for (int i = 0; i < result.length - 1; i++) {
            result[i + 1] += result[i] / 10;
            result[i] = result[i] % 10;
        }
        StringBuilder sb = new StringBuilder();
        if (result[result.length - 1] != 0) {
            sb.append((char) ('0' + result[result.length - 1]));
        }
        for (int i = 1; i < result.length; i++) {
            sb.append((char) ('0' + result[result.length - i - 1]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution_43().multiply("123", "45"));
        System.out.println(new Solution_43().multiply("980", "79"));
    }
}
