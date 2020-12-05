package round1;

public class L221_maximalSquare {
    public static int maximalSquare(String[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = Integer.valueOf(matrix[0][0]);
        int max = dp[0][0];

        for (int j = 0; j < n; j++) {
            dp[0][j] = Integer.valueOf(matrix[0][j]);
            max = Math.max(max, dp[0][j]);
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = Integer.valueOf(matrix[i][0]);
            max = Math.max(max, dp[i][0]);
        }

        for (int i = 1; i < m; i++) {
            for (int j =1; j < n; j++) {
                if (Integer.valueOf(matrix[i][j]) == 0) dp[i][j] = 0;
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max*max;
    }

    public static void main (String[] args) {
        String[][] matrix = {{"1","0","1","0","0"},{"1","0","1","1","1"},{"1","1","1","1","1"},{"1","0","0","1","0"}};

        System.out.print(maximalSquare(matrix));
    }
}
