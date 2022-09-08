package fun.re1ife.great.class01;

/**
 * Don't worry, be happy
 *
 * @author re1ife
 * @date 2022/09/06 09:33
 **/
public class Code05_LongestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, process(matrix, i, j, dp));
            }
        }
        return ans;
    }

    public int process(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            p1 = process(matrix, i + 1, j, dp);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            p2 = process(matrix, i, j + 1, dp);
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            p3 = process(matrix, i - 1, j, dp);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            p4 = process(matrix, i, j - 1, dp);
        }
        dp[i][j] = Math.max(p1, Math.max(Math.max(p2, p3), p4)) + 1;
        return dp[i][j];

    }

}
