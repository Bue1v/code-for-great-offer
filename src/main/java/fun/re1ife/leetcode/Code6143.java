package fun.re1ife.leetcode;

/**
 * Don't worry, be happy
 *
 * [11,12,74,67,37,87,42,34,18,90,36,28,34,20]
 * [18,98,2,84,7,57,54,65,59,91,7,23,94,20]
 * 937
 *
 * @author re1ife
 * @date 2022/09/03 23:15
 **/
public class Code6143 {

    public static void main(String[] args) {
        int[] t = {11,12,74,67,37,87,42,34,18,90,36,28,34,20};
        int[] c = {18,98,2,84,7,57,54,65,59,91,7,23,94,20};
        long b = 937;
        process(t, c, b, 8, 0, 0, 8);
    }

    public static int maximumRobots(int[] cT, int[] rC, long b) {
        for (int i = 1; i < 10000; i++) {
            if (!process(cT, rC, b, i, 0, 0, i)) {
                return i - 1;
            }
        }
        return 0;
    }

    /**
     * 必须要再拿restC个，预算还剩restB个，当前来到index位置
     */
    public static boolean process(int[] cT, int[] rC, long restB, int restC, int index, long curMax, int k) {
        if (restC == 0) {
            return restB >= 0;
        }
        if (index == cT.length) {
            return restC == 0 && restB > 0;
        }
        if (restB < 0) {
            return false;
        }
        //当前充电时间是否要补充
        long curC = Math.max(curMax, cT[index]) - curMax;
        long curR = (long)k * (long)rC[index];
        long cur = curR + curC;
        boolean p1 = process(cT, rC, restB - cur, restC - 1, index + 1, curMax + curC, k);
        boolean p2 = process(cT, rC, restB, restC, index + 1, curMax, k);
        return p1 || p2;
    }


}
