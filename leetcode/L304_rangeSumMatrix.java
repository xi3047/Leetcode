package leetcode;

/*
    @author: Xi Zhang
    @date:   1/25/19
    @time:   5:36 PM
 */

class L304_rangeSumMatrix {
    private int[][] dp;

    public L304_rangeSumMatrix(int[][] matrix) {
        if(   matrix           == null
                || matrix.length    == 0
                || matrix[0].length == 0   ){
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] -dp[i - 1][j - 1] + matrix[i - 1][j - 1] ;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
