package com.backinfile.lintCode;

import org.junit.Test;

import java.util.Arrays;

public class Solution_444 {

    private int[] map = new int[10000005];
    private boolean isTree = true;
    private int edgeNum = 0;
    private int pointNum = 0;


    /**
     * @param a: the node a
     * @param b: the node b
     * @return: nothing
     */
    public void addEdge(int a, int b) {
        if (!isTree) {
            return;
        }
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) {
            isTree = false;
            return;
        }

        if (map[a] == 0) {
            pointNum++;
            map[a] = a;
        }
        if (map[b] == 0) {
            pointNum++;
            map[b] = b;
        }
        edgeNum++;

        map[aRoot] = bRoot;
    }

    /**
     * @return: check whether these edges make up a valid tree
     */
    public boolean isValidTree() {
        // write your code here
        return isTree && (pointNum == edgeNum + 1);
    }

    private int find(int x) {
        if (map[x] == 0 || map[x] == x) {
            return x;
        }
        int result = find(map[x]);
        map[x] = result;
        return result;
    }


    @Test
    public void test() {
        addEdge(1, 2);
        assert isValidTree();
        addEdge(1, 3);
        assert isValidTree();
        addEdge(1, 5);
        assert isValidTree();
        addEdge(3, 5);
        assert !isValidTree();
    }
}
