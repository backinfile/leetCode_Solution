public class Solution_1138 {
    public String alphabetBoardPath(String target) {
        int x = 0;
        int y = 0;
        int last = 0;
        StringBuilder result = new StringBuilder();
        for (int s = 0; s < target.length(); s++) {
            int codePoint = target.codePointAt(s);
            if (codePoint == last) {
                result.append('!');
                continue;
            }
            int diff = codePoint - 'a';
            if (last == 'z') {
                diff = 'u' - 'a';
                result.append('U');
            }

            int targetX = diff % 5;
            int targetY = diff / 5;
            for (int i = x; i < targetX; i++) {
                result.append('R');
            }
            for (int i = targetX; i < x; i++) {
                result.append('L');
            }
            for (int i = y; i < targetY; i++) {
                result.append('D');
            }
            for (int i = targetY; i < y; i++) {
                result.append('U');
            }
            if (codePoint == 'z') {
                result.append('D');
            }
            result.append('!');

            x = targetX;
            y = targetY;
            last = codePoint;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int code = 'a';
        System.out.println(code);
    }
}
