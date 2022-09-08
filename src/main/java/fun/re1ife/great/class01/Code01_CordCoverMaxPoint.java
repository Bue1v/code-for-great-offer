package fun.re1ife.great.class01;

/**
 * Don't worry, be happy
 * <p>
 * 一条长度为K的绳子，最多能盖住几个点？
 *
 * @author re1ife
 * @date 2022/09/01 09:08
 **/
public class Code01_CordCoverMaxPoint {

    public static int maxPoint1(int[] arr, int L) {
        int n = arr.length;
        int max = 0;
        int l = 0;
        int r = 0;
        while (l < n) {
            //在每个l固定的位置，让r尽可能往右，看看盖住了几个点
            while (r < n && arr[r] - arr[l] <= L) {
                r++;
            }
            max = Math.max(max, r - (l++));
        }
        return max;
    }
}
