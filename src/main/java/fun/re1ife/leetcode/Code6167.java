package fun.re1ife.leetcode;

import java.util.HashMap;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/04 10:36
 **/
public class Code6167 {

    public static void main(String[] args) {
        String s = "abaccb";
        int[] d = {1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(checkDistances(s, d));

    }

    public static boolean checkDistances(String s, int[] distance) {
        int[] help = new int[26];
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i ++) {
            int index = str[i] - 'a';
            help[index] = i - help[index];
        }
        for (int i = 0; i < 26; i++) {
            help[i] --;
        }
        for (int i = 0; i < 26; i ++) {
            if (distance[i] != help[i] && help[i] != -1) {
                return false;
            }
        }
        return true;


    }

    public int numberOfWays(int s, int e, int k) {
        HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<>();
        return process(e, s, k, dp);
    }

    public int process(int e, int cur, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {

        if (dp.containsKey(cur) && dp.get(cur).containsKey(rest)) {
            return dp.get(cur).get(rest);
        }
        if (rest == 0) {
            return cur == e ? 1 : 0;
        }
        long p1 = process(e, cur + 1, rest - 1, dp);
        long p2 = process(e, cur - 1, rest - 1, dp);
        if (!dp.containsKey(cur)) {
            dp.put(cur, new HashMap<>());
        }
        dp.get(cur).put(rest, (int)((p1 + p2) % (int)(Math.pow(10, 9) + 7)));

        return dp.get(cur).get(rest);


    }
}
