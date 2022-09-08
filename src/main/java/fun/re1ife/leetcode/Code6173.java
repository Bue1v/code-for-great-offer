package fun.re1ife.leetcode;

import java.util.*;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/03 22:45
 **/
public class Code6173 {
    public int maximumRows(int[][] mat, int cols) {
        int n = mat.length;
        int m = mat[0].length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0 ; i < m; i ++) {
            map.put(i, new HashSet<>());
            for (int j = 0; j < n; j ++) {
                if (mat[j][i] == 1) {
                    map.get(i).add(j);
                }
            }
        }


        return 0;

    }

    public int process(int[][] mat, int index, int rest, Map<Integer, Set<Integer>> map) {
        if (rest == 0) {
            boolean[] b = new boolean[mat.length];
            Arrays.fill(b, true);
            for (int i = 0; i < mat.length; i ++) {
                for (int j = 0; j < mat[0].length; j ++) {
                    if (mat[i][j] == 1) {
                        b[i] = false;
                    }
                }
            }
            int ans = 0;
            for (int i = 0; i < b.length; i++) {
                if(b[i]) {
                    ans ++;
                }
            }
            return ans;
        }
        if (index == mat[0].length) {
            return 0;
        }
        int ans = 0;
        int p1 = process(mat, index + 1, rest, map);
        for (int i = 0; i < mat.length; i++) {
            mat[i][index] = 0;
        }
        int p2 = process(mat, index + 1, rest - 1, map);
        for (Integer integer : map.get(index)) {
            mat[integer][index] = 1;
        }
        return p1 + p2;

    }

}
