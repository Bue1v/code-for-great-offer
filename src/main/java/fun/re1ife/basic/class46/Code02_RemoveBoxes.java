package fun.re1ife.basic.class46;

/**
 * Don't worry, be happy
 *
 * https://leetcode.cn/problems/remove-boxes/
 *
 * @author re1ife
 * @date 2022/09/01 10:54
 **/
public class Code02_RemoveBoxes {

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return process(boxes, 0, boxes.length - 1, 0, dp);
    }

    /**
     *
     * 从 l ~ r 位置可获得的最大积分， 前面有k个数与arr[l]相等
     *
     * @param boxes
     * @param l
     * @param r
     * @param k
     * @return
     */
    public int process(int[] boxes, int l, int r, int k, int[][][] dp) {
        if (l > r) {
            return 0;
        }
        if (dp[l][r][k] != 0) {
            return dp[l][r][k];
        }
        //k个数和 l 位置一起消掉
        int ans = (k + 1) * (k + 1) + process(boxes, l + 1, r, 0, dp);
        for (int i = l + 1; i <= r; i++) {
            if (boxes[l] == boxes[i]) {
                int left = process(boxes, l + 1, i - 1, 0, dp);
                int right = process(boxes, i, r, k + 1, dp);
                ans = Math.max(ans, left + right);
            }
        }
        dp[l][r][k] = ans;
        return ans;
    }

    public int removeBoxes2(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return process2(boxes, 0, boxes.length - 1, 0, dp);
    }

    /**
     *
     * 从 l ~ r 位置可获得的最大积分， 前面有k个数与arr[l]相等
     * 优化常数项版本
     * 如果 之前的k个位置和arr[l]相等， 这 k + 1 个数一定可以一起消掉
     * 同样的，在l到r范围伤如果还存在同样与之前k个数相等的连续个数，只需要调第一次，后面连续的都可以跳过
     *
     *
     * @param boxes
     * @param l
     * @param r
     * @param k
     * @return
     */
    public int process2(int[] boxes, int l, int r, int k, int[][][] dp) {
        if (l > r) {
            return 0;
        }
        if (dp[l][r][k] > 0) {
            return dp[l][r][k];
        }

        //算出连续的最后一个与l位置数相等的位置
        int last = l;
        while (last + 1 <= r && boxes[last + 1] == boxes[l]) {
            last++;
        }
        //算出当前有多少个数可以一起消掉
        int pre = k + last - l + 1;
        //第一种case就是前面的连着一起消，加上process后面的
        int ans = pre * pre + process2(boxes, last + 1, r, 0, dp);

        //last + 1位置上面算过了，这边从last + 2开始算
        for (int i = last + 2; i <= r; i++) {
            //与l位置相等 连续的只计算第一个 加速过程
            if (boxes[l] == boxes[i] && boxes[i - 1] != boxes[l]) {
                int left = process2(boxes, last + 1, i - 1, 0, dp);
                //相等的已经有pre个了
                int right = process2(boxes, i, r, pre, dp);
                ans = Math.max(ans, left + right);
            }
        }
        dp[l][r][k] = ans;
        return ans;
    }

    public int process3(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        int last = L;
        while (last + 1 <= R && boxes[last + 1] == boxes[L]) {
            last++;
        }
        int pre = K + last - L + 1;
        int ans = pre * pre + process2(boxes, last + 1, R, 0, dp);
        for (int i = last + 2; i <= R; i++) {
            if (boxes[L] == boxes[i] && boxes[i - 1] != boxes[L]) {
                ans = Math.max(ans, process2(boxes, last + 1, i - 1, 0, dp) + process2(boxes, i, R, pre, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

}
