package fun.re1ife.basic.class46;

/**
 * Don't worry, be happy
 * <p>
 * 打爆气球可获得的最大分数
 *
 * @author re1ife
 * @date 2022/09/01 10:09
 **/
public class Code01_BurstBalloons {

    public int burstBalloons(int[] arr) {
        int[] help = new int[arr.length + 2];
        int index = 0;
        for (int i = 1; i < help.length - 1; i++) {
            help[i] = arr[index ++];
        }
        help[arr.length + 1] = 1;
        return process(help, 1, arr.length);

    }

    /**
     * 在 l ~ r 范围上打爆气球， 规定l - 1和r + 1位置气球一定还没爆才能调用该函数
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public int process(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l - 1] * arr[l] * arr[r + 1];
        }
        int max = arr[l - 1] * arr[l] * arr[r + 1] + process(arr, l + 1, r);
        max = Math.max(max, arr[l - 1] * arr[r] * arr[r + 1] + process(arr, l, r - 1));
        for (int i = l + 1; i < r; i++) {
            int left = process(arr, l, i - 1);
            int right = process(arr, i + 1, r);
            int last = arr[l - 1] * arr[i] * arr[r + 1];
            max = Math.max(max, left + right + last);
        }
        return max;
    }

}
