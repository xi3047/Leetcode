package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/28/2020 7:22 PM
 * @topic round2.dp
 * @link https://leetcode.com/problems/maximal-square/
 * @description
 */
public class L221_MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int [][] dp = new int [row][col];
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0) {
                    dp[i][j] =  matrix[i][j] - '0';
                    max = Math.max(max, dp[i][j]);
                }
                else if(j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                    max = Math.max(max, dp[i][j]);
                }
                else if (matrix[i][j] - '0' == 0) {
                    dp[i][j] = 0;
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    /*
    Create extra rol and col,
    so no need to process first row and col individually
     */
    public int maximalSquare2(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
