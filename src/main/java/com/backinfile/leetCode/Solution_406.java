package com.backinfile.leetCode;

import com.alibaba.fastjson2.JSON;

import java.util.*;

public class Solution_406 {
    public static void main(String[] args) {
        printArr(new Solution_406().reconstructQueue(JSON.parseObject("[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]", int[][].class)));
        printArr(new Solution_406().reconstructQueue(JSON.parseObject("[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]", int[][].class)));
    }

    private static void printArr(int[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }


    public static class People implements Comparable<People> {
        public final int height;
        public final int index;

        public People(int height, int index) {
            this.height = height;
            this.index = index;
        }

        @Override
        public int compareTo(People o) {
            if (height == o.height) {
                return Integer.compare(this.index, o.index);
            }
            return -Integer.compare(height, o.height);
        }

        @Override
        public String toString() {
            return "(" + height + "," + index + ")";
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        LinkedList<People> peopleList = new LinkedList<>();
        for (int[] person : people) {
            peopleList.add(new People(person[0], person[1]));
        }

        Collections.sort(peopleList);

        LinkedList<People> answer = new LinkedList<>();


        for (People p : peopleList) {
            answer.add(p.index, p);
        }


        for (int i = 0; i < answer.size(); i++) {
            People p = answer.get(i);
            people[i][0] = p.height;
            people[i][1] = p.index;
        }
        return people;
    }

}
