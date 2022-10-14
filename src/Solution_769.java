public class Solution_769 {
    public int maxChunksToSorted(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }


        int groupCount = 0;
        int[] groupStartIndex = new int[arr.length];
        int[] groupMaxValue = new int[arr.length];


        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            boolean mergeInGroup = false;
            for (int g = 0; g < groupCount; g++) {
                int startIndex = groupStartIndex[g];
                int maxValue = groupMaxValue[g];
                if (value < maxValue) {
                    mergeInGroup = true; // 合并当前值进入此分组
                    for(int cur = g + 1; cur < groupCount; cur++){ // 计算最大值
                        groupMaxValue[g] = Math.max(groupMaxValue[g], groupMaxValue[cur]);
                    }
                    groupCount = g + 1;
                    break;
                }
            }
            if (!mergeInGroup) { // 新建一个group
                groupStartIndex[groupCount] = i;
                groupMaxValue[groupCount] = value;
                groupCount++;
            }
        }
        return groupCount;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_769().maxChunksToSorted(new int[]{1,4,0,2,3,5}));
    }
}
