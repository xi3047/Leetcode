package round1;

public class L64_minPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m][n] = grid[m][n];

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0 ; j--) {
                if (i == m - 1 && j != n - 1) dp[i][j] = grid[i][j] + dp[i][j + 1];
                else if (j == n -1 && i != m -1) dp[i][j] = grid[i][j] + dp[i - 1][j];
                else if (i != m -1 && j != n -1) dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                else dp[i][j] = grid[i][j];
            }
        }

        return dp[0][0];
    }
}
