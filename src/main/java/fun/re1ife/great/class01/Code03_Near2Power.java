package fun.re1ife.great.class01;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/07 14:43
 **/
public class Code03_Near2Power {
    // 已知n是正数
    // 返回大于等于，且最接近n的，2的某次方的值
    public static final int tableSizeFor(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

}
