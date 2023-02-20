package com.backinfile.lintCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_790 {
    /**
     * @param generator:    Generating set of rules.
     * @param startSymbol:  Start symbol.
     * @param symbolString: Symbol string.
     * @return: Return true if the symbol string can be generated, otherwise return false.
     */
    public boolean canBeGenerated(String[] generator, char startSymbol, String symbolString) {
        // Write  your code here.
        Map<Character, List<String>> generatorMap = new HashMap<>();
        for (String str : generator) {
            String[] split = str.split(" -> ");
            generatorMap.computeIfAbsent(split[0].charAt(0), key -> new ArrayList<>()).add(split[1]);
        }
        return match(generatorMap, startSymbol, symbolString, 0) == symbolString.length();
    }

    // 返回匹配了多少的字符，返回-1表示失败
    private int match(Map<Character, List<String>> generatorMap, char startSymbol, String symbolString, int symbolIndex) {
        List<String> paths = generatorMap.get(startSymbol);
        if (paths == null || paths.isEmpty()) {
            return -1;
        }

        PATH:
        for (String path : paths) {
            int matchOffset = 0;
            for (int pathIndex = 0; pathIndex < path.length(); pathIndex++) {
                char match = path.charAt(pathIndex);
                int curIndex = symbolIndex + matchOffset;
                if (curIndex >= symbolString.length()) {
                    continue PATH;
                }
                if (Character.isUpperCase(match)) {
                    int result = match(generatorMap, match, symbolString, curIndex);
                    if (result < 0) {
                        continue PATH;
                    }
                    matchOffset += result;
                } else {
                    if (match == symbolString.charAt(curIndex)) {
                        matchOffset++;
                    } else {
                        continue PATH;
                    }
                }
            }
            return matchOffset;
        }
        return -1;
    }

    @Test
    public void test() {
        assert canBeGenerated(new String[]{"S -> abc", "S -> aA", "A -> b", "A -> c"}, 'S', "ac");
        assert canBeGenerated(new String[]{"S -> abcd", "S -> A", "A -> abc"}, 'S', "abc");
        assert !canBeGenerated(new String[]{"S -> abc", "S -> aA", "A -> b", "A -> c"}, 'S', "a");
    }
}
