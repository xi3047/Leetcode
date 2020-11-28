package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/22/2020 11:35 PM
 * @topic round2.dp
 * @link
 * @description
 */
public class L63_UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else if ( i == 0 && j == 0) dp[i][j] = 1;
                else if ( i == 0 && j > 0) dp[i][j] += dp[i][j-1];
                else if ( j == 0 && i > 0) dp[i][j] += dp[i-1][j];
                else dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m-1][n-1];
    }
}
