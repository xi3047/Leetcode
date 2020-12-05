package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/22/2020 8:55 PM
 * @topic round2.dp
 * @link
 * @description
 */
public class L70_ClimbingStairs {
    /**
     * Solution 1
     * Brute Force Solution
     * Time: O(2^n)
     */
    public int climbStairs1(int n) {
        return climb_Stairs1(0, n);
    }
    public int climb_Stairs1(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs1(i + 1, n) + climb_Stairs1(i + 2, n);
    }

    /**
     * Solution 2
     * Recursion with Memoization
     * Time: O(n)
     */
    public int climbStairs2(int n){
        int[] memo = new int[n + 1];
        return climbStairs2(0, n, memo);
    }
    public int climbStairs2(int i, int n, int[] memo) {
        if (i > n) return 0;
        if (i == n) return 1;
        if (memo[i] > 0) return memo[i];
        memo[i] = climbStairs2(i + 1, n, memo) + climbStairs2(i + 2, n, memo);
        return memo[i];
    }

    /**
     * Dynamic Programming
     */
    public int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
