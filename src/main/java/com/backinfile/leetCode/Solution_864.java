package com.backinfile.leetCode;

import java.util.*;

public class Solution_864 {
    public static void main(String[] args) {
        System.out.println(new Solution_864().shortestPathAllKeys(new String[]{"@.a..", "###.#", "b.A.B"})); // 8
        System.out.println(new Solution_864().shortestPathAllKeys(new String[]{"@..aA", "..B#.", "....b"})); // 6
        System.out.println(new Solution_864().shortestPathAllKeys(new String[]{"@Aa"})); // -1
        System.out.println(new Solution_864().shortestPathAllKeys(new String[]{"@...a", ".###A", "b.BCc"})); // 10
        System.out.println(new Solution_864().shortestPathAllKeys(new String[]{"#..#.#.#..#.#.#.....#......#..",".#.......#....#A.....#.#......","#....#.....#.........#........","...#.#.........#..@....#....#.",".#.#.##...#.........##....#..#","..........#..#..###....##..#.#",".......#......#...#...#.....c#",".#...#.##......#...#.###...#..","..........##...#.......#......","#...#.........a#....#.#.##....","..#..#...#...#..#....#.....##.","..........#...#.##............","...#....#..#.........#..D.....","....#E.#....##................","...........##.#.......#.#....#","...#..#...#.#............#e...","..#####....#.#...........##..#","##......##......#.#...#..#.#..",".#F.......#..##.......#....#..","............#....#..#..#...#..",".............#...#f...#..##...","....#..#...##.........#..#..#.",".....#.....##.###..##.#......#",".#..#.#...#.....#........###..",".....#.#...#...#.....#.....#..","##.....#....B.....#..#b.......",".####....##..#.##..d.#......#.","..#.....#....##........##...##","...#...#...C..#..#....#.......","#.....##.....#.#......#......."}));
    }


    private String[] grid;
    private int xMax;
    private int yMax;
    private int maskMax;

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
        this.maskMax = (1 << keyNum) - 1;

        return bfs(startX, startY);
    }

    private static class Tuple {
        public final int pos;
        public final int len;
        public final int mask;

        private Tuple(int pos, int len, int mask) {
            this.pos = pos;
            this.len = len;
            this.mask = mask;
        }
    }

    private final static int[] DX = new int[]{0, 0, 1, -1};
    private final static int[] DY = new int[]{1, -1, 0, 0};
    private int bfs(int startX, int startY) {
        boolean[][][] foot = new boolean[xMax][yMax][maskMax + 1];
        foot[startX][startY][0] = true;
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(packPos(startX, startY), 0, 0));
        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            int mask = tuple.mask;
            int len = tuple.len;
            int pos = tuple.pos;
            int x = unpackX(pos);
            int y = unpackY(pos);

            for (int i = 0; i < 4; i++) {
                int tx = x + DX[i];
                int ty = y + DY[i];
                if (0 <= tx && tx < xMax && 0 <= ty && ty < yMax) {
                    if (foot[tx][ty][mask]) {
                        continue;
                    }
                    foot[tx][ty][mask] = true;
//                    System.out.println("pass " + tx + "," + ty + " len:" + (len + 1) + " mask:" + mask);

                    int targetPos = packPos(tx, ty);
                    char ch = getChar(targetPos);
                    if (ch == '#') {
                        continue;
                    } else if (Character.isUpperCase(ch)) {
                        if (!hasKey(mask, Character.toLowerCase(ch))) {  // 被锁阻挡
                            continue;
                        }
                    } else if (Character.isLowerCase(ch)) {
                        if (!hasKey(mask, ch)) {  // 新钥匙
                            int newMask = setKey(mask, ch);
                            if (newMask == this.maskMax) { // 钥匙集齐了，不用再继续了
                                return len + 1;
                            }
                            queue.add(new Tuple(targetPos, len + 1, newMask));
                            continue;
                        }
                    }
                    queue.add(new Tuple(targetPos, len + 1, mask));
                }
            }
        }
        return -1;
    }


    public boolean hasKey(int mask, char ch) {
        int index = ch - 'a';
        return (mask & (1 << index)) != 0;
    }

    public int setKey(int mask, char ch) {
        int index = ch - 'a';
        return mask | (1 << index);
    }

    public int removeKey(int mask, char ch) {
        int index = ch - 'a';
        return mask & ~(1 << index);
    }

    public char getChar(int pos) {
        return grid[unpackX(pos)].charAt(unpackY(pos));
    }

    private static final int MAX_LEN = 100;

    private int packPos(int x, int y) {
        return x * MAX_LEN + y;
    }

    private int unpackX(int pos) {
        return pos / MAX_LEN;
    }

    private int unpackY(int pos) {
        return pos % MAX_LEN;
    }
}
