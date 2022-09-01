package fun.re1ife.basic.class46;

import java.util.LinkedList;

/**
 * Don't worry, be happy
 * <p>
 * 给定一个数组ar,和一个正数M,
 * 返回在ar的子数组在长度不超过M的情况下，最大的累加和
 * <p>
 *
 * @author re1ife
 * @date 2022/09/01 09:15
 **/
public class Code04_MaxSumLengthNoMore {

    // O(N^2)的解法，暴力解，用作对数器
    public static int test(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int L = 0; L < N; L++) {
            int sum = 0;
            for (int R = L; R < N; R++) {
                if (R - L + 1 > M) {
                    break;
                }
                sum += arr[R];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /**
     * r
     * <p>
     * 给定一个数组ar,和一个正数M, 返回在ar的子数组在长度不超过M的情况下，最大的累加和
     * O(N)复杂度的解
     *
     * @param arr 题目给定参数
     * @param M   题目给定参数
     * @return 在ar的子数组在长度不超过M的情况下，最大的累加和
     */
    public static int maxSum(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;

        int[] preSum = new int[N];
        preSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        //求1~i位置的最大累加和，就是吧窗口扩上之后，看里面最大的前缀和是多少

        int i = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        int end = Math.min(N, M);
        for (; i < end; i++) {
            while (!queue.isEmpty() && preSum[queue.peekLast()] <= preSum[i]) {
                queue.removeLast();
            }
            queue.add(i);
        }
        int max = preSum[queue.peekFirst()];
        int L = 0;
        for (;i < N; L++, i++) {
            if (queue.peekFirst() == L) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && preSum[queue.peekLast()] <= preSum[i]) {
                queue.removeLast();
            }
            queue.add(i);
            max = Math.max(max, preSum[queue.peekFirst()] - preSum[L]);
        }
        for (;L < N - 1; L ++) {
            if (queue.peekFirst() == L) {
                queue.removeFirst();
            }
            max = Math.max(max, preSum[queue.peekFirst()] - preSum[L]);
        }

        return max;
    }

    // 用作测试
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // 用作测试
    public static void main(String[] args) {
        int maxN = 50;
        int maxValue = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int M = (int) (Math.random() * maxN);
            int[] arr = randomArray(N, maxValue);
            int ans1 = test(arr, M);
            int ans2 = maxSum(arr, M);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
