package leetcode;

public class L70_ClimbingStairs {
    public int climbStairs(int N) {
        int[] dp = new int[N];
        dp[0] = 1;
        if (N > 1) dp[1] = 2;
        for (int i = 2; i < N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[N - 1];

    }
}
