package com.backinfile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    // [[0,10000,2],[5,0,10000],[10000,4,0]]
    public static int[][] toIntArrayArray(String str) {
        return JSON.parseObject(str, int[][].class);
    }

    public static int[] toIntArray(String str) {
        return JSON.parseObject(str, int[].class);
    }

    public static List<List<Integer>> toIntIntList(String str) {
        Integer[][] array = JSON.parseObject(str, Integer[][].class);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            result.add(Arrays.asList(array[i]));
        }
        return result;
    }

    public static String readResource(String path) {
        try (InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                return "";
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return bufferedReader.lines().collect(Collectors.joining(""));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean equal(double a, double b) {
        return Math.abs(a - b) < 1e-5;
    }


    public static String[] toStrArray(String str) {
        return JSONObject.parseObject(str, String[].class);
    }

    public static String[][] toStrArrayArray(String str) {
        return JSONObject.parseObject(str, String[][].class);
    }

    public static <T extends Comparable<T>> int compareList(List<T> l1, List<T> l2) {
        for (int i = 0; i < l1.size() && i < l2.size(); i++) {
            var c = l1.get(i).compareTo(l2.get(i));
            if (c != 0) {
                return c;
            }
        }
        return l1.size() - l2.size();
    }

    public static void main(String[] args) {
//        String test = readResource("input_778_01.txt");
//        System.out.println(test);

        System.out.println(Arrays.toString(toStrArray("[\"flower\",\"flow\",\"flight\"]")));
    }
}
