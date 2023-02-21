package com.backinfile;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    // [[0,10000,2],[5,0,10000],[10000,4,0]]
    public static int[][] toIntIntArray(String str) {
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

    public static void main(String[] args) {
        String test = readResource("input_778_01.txt");
        System.out.println(test);
    }
}
