package fun.re1ife.great.class03;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/06 11:17
 **/
public class Code01_LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 1;
        //上一个位置能推倒的最远index
        int pre = 0;
        int[] map = new int[256];
        for (int i = 0; i < 256; i ++) {
            map[i] = -1;
        }
        char[] str = s.toCharArray();
        for (int i = 0; i < n; i ++) {
            int last = map[str[i]];
            pre = Math.max(last + 1, pre);
            ans = Math.max(ans, i - pre + 1);
            map[str[i]] = i;
        }
        return ans;

    }

}
