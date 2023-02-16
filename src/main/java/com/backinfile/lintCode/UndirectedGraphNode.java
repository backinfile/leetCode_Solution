package com.backinfile.lintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors = new ArrayList<UndirectedGraphNode>();

    private UndirectedGraphNode() {
    }


    // {1,2,4#2,1,4#3,5#4,1,2#5,3}
    public static List<UndirectedGraphNode> parse(String str) {
        str = str.substring(1, str.length() - 1);
        List<UndirectedGraphNode> result = new ArrayList<>();
        String[] split = str.split("#");
        for (int i = 0; i < split.length; i++) {
            result.add(new UndirectedGraphNode());
        }

        for (int i = 0; i < split.length; i++) {
            int[] nodes = Arrays.stream(split[i].split(",")).mapToInt(Integer::parseInt).toArray();
            UndirectedGraphNode graphNode = result.get(i);
            graphNode.label = nodes[0];

            for (int j = 1; j < nodes.length; j++) {
                graphNode.neighbors.add(result.get(nodes[j] - 1));
            }
        }
        return result;
    }
}
