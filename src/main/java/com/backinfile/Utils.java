package com.backinfile;

import com.alibaba.fastjson.JSON;

public class Utils {

    // [[0,10000,2],[5,0,10000],[10000,4,0]]
    public static int[][] toIntIntArray(String str) {
        return JSON.parseObject(str, int[][].class);
    }
}
