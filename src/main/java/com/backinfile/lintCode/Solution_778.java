package com.backinfile.lintCode;

import com.backinfile.Utils;
import org.junit.Test;

import java.util.*;

public class Solution_778 {
    /**
     * @param matrix: the given matrix
     * @return: The list of grid coordinates
     * we will sort your return value in output
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0) {
            return Collections.emptyList();
        }
        int width = matrix.length;
        int height = matrix[0].length;


        int[][] foot = new int[width][height];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            queue.add(pack(i, 0));
        }
        for (int i = 1; i < height; i++) {
            queue.add(pack(0, i));
        }
        HashSet<Integer> tops = spreadUp(matrix, queue);

        for (int i = 0; i < width; i++) {
            queue.add(pack(i, height - 1));
        }
        for (int i = 0; i < height; i++) {
            queue.add(pack(width - 1, i));
        }
        HashSet<Integer> bottoms = spreadUp(matrix, queue);
        tops.retainAll(bottoms);
        List<List<Integer>> result = new ArrayList<>();
        for (int pos : tops) {
            result.add(Arrays.asList(pos / FACTOR, pos % FACTOR));
        }
        return result;
    }

    private HashSet<Integer> spreadUp(int[][] matrix, LinkedList<Integer> queue) {
        HashSet<Integer> visited = new HashSet<>();

        int width = matrix.length;
        int height = matrix[0].length;

        while (!queue.isEmpty()) {
            int pos = queue.poll();
            if (visited.contains(pos)) {
                continue;
            }
            visited.add(pos);
            int x = pos / FACTOR;
            int y = pos % FACTOR;

            int curHeight = matrix[x][y];
            for (int i = 0; i < dx.length; i++) {
                int fx = x + dx[i];
                int fy = y + dy[i];
                if (fx < 0 || fy < 0 || fx >= width || fy >= height) {
                    continue;
                }
                int nextHeight = matrix[fx][fy];
                if (nextHeight < curHeight) { // 向更高处查找
                    continue;
                }
                queue.add(fx * FACTOR + fy);
            }
        }
        return visited;
    }

    private static final int FACTOR = 1 << 8;
    private static final int[] dx = new int[]{0, 0, 1, -1};
    private static final int[] dy = new int[]{1, -1, 0, 0};

    private static int pack(int x, int y) {
        return x * FACTOR + y;
    }


    @Test
    public void test() {
        assert testEquals("[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]",
                "[[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]");
        assert testEquals("[[1,2],[4,3]]",
                "[[0,1],[1,0],[1,1]]");
        assert testEquals(Utils.readResource("input_778_01.txt"),
                "[[0,108],[1,107],[107,1],[108,0],[108,1]]");
    }

    private boolean testEquals(String inputStr, String answerStr) {
        List<List<Integer>> result = pacificAtlantic(Utils.toIntArrayArray(inputStr));
        List<List<Integer>> answerList = Utils.toIntIntList(answerStr);

        sortResult(result);
        sortResult(answerList);
        return result.equals(answerList);
    }

    private void sortResult(List<List<Integer>> list) {
        list.sort((o1, o2) -> {
            for (int i = 0; i < o1.size(); i++) {
                int cmp = o1.get(i) - o2.get(i);
                if (cmp != 0) {
                    return cmp;
                }
            }
            return 0;
        });
    }
}
