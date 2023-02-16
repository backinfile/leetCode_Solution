package com.backinfile.lintCode;

import org.junit.Test;

public class Solution_128 {
    /**
     * @param key:       A string you should hash
     * @param hASH_SIZE: An integer
     * @return: An integer
     */
    public int hashCode(String key, int hASH_SIZE) {
        // write your code here
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (int)((key.codePointAt(i)+ hash * 33L) % hASH_SIZE);
        }
        return hash;
    }


    @Test
    public void test() {
        assert hashCode("abcd", 1000) == 978;
        assert hashCode("abcd", 100) == 78;
        assert hashCode("Wrong answer or accepted?", 1000000007) == 382528955;
    }
}
