package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/22/2020 9:37 PM
 * @topic round2.dp
 * @link
 * @description
 */
public class L509_FibonacciNumber {
    /**
     * Recursion with memoization
     */
    public int fib(int N) {
        int [] memo = new int[N + 1];
        return fib(N, memo);

    }

    private int fib(int n, int[] memo) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo[n] > 0) return memo[n];
        memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        return memo[n];
    }


    /**
     * DP
     */
    public int fibDP(int N) {
        if (N == 0) return 0;
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[N];
    }
}
