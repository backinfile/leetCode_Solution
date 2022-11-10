import java.util.*;

public class Solution_864_TLE {

    public static void main(String[] args) {
        System.out.println(new Solution_864_TLE().shortestPathAllKeys(new String[]{"@.a..", "###.#", "b.A.B"})); // 8
        System.out.println(new Solution_864_TLE().shortestPathAllKeys(new String[]{"@..aA", "..B#.", "....b"})); // 6
        System.out.println(new Solution_864_TLE().shortestPathAllKeys(new String[]{"@Aa"})); // -1

    }


    private String[] grid;
    private int xMax;
    private int yMax;

    public int shortestPathAllKeys(String[] grid) {
        this.grid = grid;
        this.xMax = grid.length;
        this.yMax = grid[0].length();

        int keyNum = 0;
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                char ch = grid[i].charAt(j);
                if (Character.isLowerCase(ch)) {
                    keyNum++;
                } else if (ch == '@') {
                    startX = i;
                    startY = j;
                }
            }
        }
        int step = bfs(keyNum, new HashSet<>(), packPos(startX, startY));
        return step == Integer.MAX_VALUE ? -1 : step;
    }

    private int bfs(int leftKeyNum, Set<Character> ownKeys, int pos) {
        if (leftKeyNum <= 0) {
            return 0;
        }

        int minStep = Integer.MAX_VALUE;
        Map<Integer, Integer> reachKey = findReachKey(leftKeyNum, ownKeys, pos);
        for (Map.Entry<Integer, Integer> entry : reachKey.entrySet()) {
            int targetPos = entry.getKey();
            int step = entry.getValue();
            char key = getChar(targetPos);

            ownKeys.add(key);
            int dpsStep = bfs(leftKeyNum - 1, ownKeys, targetPos);
            int finalStep = dpsStep != Integer.MAX_VALUE ? (step + dpsStep) : Integer.MAX_VALUE;
            ownKeys.remove(key);
            if (finalStep < minStep) {
                minStep = finalStep;
            }
        }
        return minStep;
    }

    private final static int[] DX = new int[]{0, 0, 1, -1};
    private final static int[] DY = new int[]{1, -1, 0, 0};

    public Map<Integer, Integer> findReachKey(int leftKeyNum, Set<Character> ownKeys, int startPos) {
        Queue<Long> queue = new LinkedList<>();
        queue.add(packPath(startPos, 0));

        Map<Integer, Integer> findKeys = new HashMap<>();
        Set<Character> findKeySet = new HashSet<>();
        Set<Integer> foot = new HashSet<>();

        while (!queue.isEmpty()) {
            long path = queue.poll();
            int pos = unpackPos(path);

            int len = unpackLen(path);
            char ch = getChar(pos);

            if (ch == '#') { // 墙
                continue;
            } else if (Character.isUpperCase(ch)) { // 被锁阻挡
                if (!ownKeys.contains(Character.toLowerCase(ch))) {
                    continue;
                }
            } else if (Character.isLowerCase(ch)) { // 新钥匙
                if (!ownKeys.contains(ch) && !findKeySet.contains(ch)) {
                    findKeySet.add(ch);
                    findKeys.put(pos, len);
                    leftKeyNum--;
                    if (leftKeyNum <= 0) {
                        break;
                    }
                }
            }
            foot.add(pos);

            int x = unpackX(pos);
            int y = unpackY(pos);
            for (int i = 0; i < 4; i++) {
                int tx = x + DX[i];
                int ty = y + DY[i];
                if (0 <= tx && tx < xMax && 0 <= ty && ty < yMax) {
                    int targetPos = packPos(tx, ty);
                    if (!foot.contains(targetPos)) {
                        queue.add(packPath(targetPos, len + 1));
                    }
                }
            }
        }
        return findKeys;
    }

    public char getChar(int pos) {
        return grid[unpackX(pos)].charAt(unpackY(pos));
    }

    private static final int MAX_LEN = 1000;
    private static final long MAX_LEN_PATH = MAX_LEN * MAX_LEN;

    private int packPos(int x, int y) {
        return x * MAX_LEN + y;
    }

    private int unpackX(int pos) {
        return pos / MAX_LEN;
    }

    private int unpackY(int pos) {
        return pos % MAX_LEN;
    }

    private long packPath(int pos, int len) {
        return len * MAX_LEN_PATH + pos;
    }

    private int unpackPos(long path) {
        return (int) (path % MAX_LEN_PATH);
    }

    private int unpackLen(long path) {
        return (int) (path / MAX_LEN_PATH);
    }
}
