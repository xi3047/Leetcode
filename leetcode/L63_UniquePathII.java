package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-03-09
    @time:   14:56
 */
import org.junit.Test;

public class L63_UniquePathII {

    // Solution1: 2D DP
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
                else if ( j == 0 && i == 0) dp[i][j] = 1;
                else if ( j == 0 && i > 0) dp[i][j] += dp[i-1][j];

                else dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m-1][n-1];
    }

    // Solution 2: 1D DP
    public int uniquePathsWithObstacles1D(int[][] grid) {
        int width = grid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : grid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1) dp[j] = 0;
                else if (j > 0) dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }

    @Test
    public void test() {
        int [][] grid = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int [][] grid2 = new int[][]{{1,0}};
        System.out.println(uniquePathsWithObstacles(grid2));
    }
}
