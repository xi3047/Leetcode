package round1;

public class L72_editDistance {
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];

        dp[0][0] = 0;
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = min(dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + (word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1));
            }
        }
        return dp[m][n];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a,Math.min(b,c));
    }

    public static void main(String[] args) {
        System.out.print(minDistance("ros","horse"));
    }
}
