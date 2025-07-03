package round4.DP;

public class L1209_DominoTromino {
    public int numTilings(int n) {
        int mod = 1_000_000_007;
        long[][] dp = new long[n + 1][3];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + 2 * dp[i -1][1]) % mod;
            dp[i][1] = (dp[i - 2][0] + dp[i -  1][1]) % mod;
        }
        return (int) dp[n][0];
    }
}
