package com.backinfile.lintCode;

import com.backinfile.Utils;
import org.junit.Test;

import java.util.PriorityQueue;

@SuppressWarnings("ConstantConditions")
public class Solution_518 {

    /**
     * @param n:      a positive integer
     * @param primes: the given prime list
     * @return: the nth super ugly number
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        Heap heap = new Heap(n * primes.length);
        heap.add((1L << 32) + 1);
        for (int i = 0; i < n - 1; i++) {
            long value = heap.poll();
            int v = (int) (value >> 32);
            int p = (int) (value & 0xFFFF);
//            System.out.printf("get v = %d p = %d%n", v, p);
            for (int prime : primes) {
                if (prime >= p) {
                    long result = ((long) v) * prime;
                    if (result == (int) result) {
                        heap.add((result << 32) + prime);
//                    System.out.println("add value " + prime * v);
                    }
                }
            }
        }
        int result = (int) (heap.poll() >> 32);
//        System.out.println("result = " + result);
        return result;
    }

    // 超出size的弃掉
    public static class Heap {
        private final long[] arr;
        private int length = 0;

        public Heap(int size) {
            arr = new long[size + 1];
        }

        public void add(long value) {
            arr[length] = value;
            length++;
            orderUp(length - 1);

            if (length == arr.length - 1) {
                length--;
            }
        }

        public long poll() {
            if (length == 1) {
                length = 0;
                return arr[0];
            }

            long result = arr[0];
            arr[0] = arr[length - 1];
            length -= 1;
            orderDown(0);
            return result;
        }

        private void orderDown(int cur) {
            int left = cur * 2 + 1;
            while (left < length) {
                int right = left + 1;
                long min = arr[left];
                int child = left;
                if (right < length && arr[right] < min) {
                    min = arr[right];
                    child = right;
                }
                long curValue = arr[cur];
                if (curValue < min) { // 顶部是最小的，无需变化
                    break;
                }
                arr[cur] = min;
                arr[child] = curValue;

                cur = child;
                left = cur * 2 + 1;
            }
        }

        private void orderUp(int cur) {
            int parent = (cur - 1) / 2;

            while (parent != cur) {
                long parentValue = arr[parent];
                long curValue = arr[cur];
                if (curValue >= parentValue) {
                    break;
                }
                arr[parent] = curValue;
                arr[cur] = parentValue;

                cur = parent;
                parent = (cur - 1) / 2;
            }

        }

        public boolean isEmpty() {
            return length == 0;
        }
    }


    @Test
    public void test() {
        assert nthSuperUglyNumber(6, Utils.toIntArray("[2,7,13,19]")) == 13;
        assert nthSuperUglyNumber(11, Utils.toIntArray("[2,3,5]")) == 15;
        assert nthSuperUglyNumber(394, Utils.toIntArray("[31,151,97,67,353,271,101,37]")) == 183694369;
        assert nthSuperUglyNumber(215, Utils.toIntArray("[47,61,43,73,79,7,29,19,31,67,3,11]")) == 4599;
    }
}
