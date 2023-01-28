package sword_offer;

import org.junit.Test;

import java.util.stream.IntStream;

// 找到1~n序列中的重复数字
public class Offer_P41 {

    @Test
    public void test() {
        assert findDuplicate(new int[]{2, 1, 1}) == 1;
        assert findDuplicate(new int[]{1, 2, 2}) == 2;
        assert findDuplicate(new int[]{2, 3, 5, 4, 1, 2, 6, 8, 7}) == 2;
        assert findDuplicate(new int[]{8, 2, 3, 5, 4, 1, 2, 6, 7}) == 2;
        assert findDuplicate(new int[]{8, 2, 3, 5, 4, 1, 8, 6, 7}) == 8;
    }

    // 允许修改
    public int findDuplicate(int[] arr) {
        assert arr.length > 0;
        assert arr.length >= IntStream.of(arr).max().getAsInt();

        for (int i = 0; i < arr.length; i++) {
            int curValue = arr[i];
            while (curValue - 1 != i) {
                int nextIndex = curValue - 1;
                int nextValue = arr[nextIndex];
                if (nextValue == curValue) {
                    return nextValue;
                }

                arr[nextIndex] = curValue;
                arr[i] = nextValue;
                curValue = nextValue;
            }
        }
        assert false;
        return -1;
    }
}
