package round2.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Xi Zhang
 * @date 12/28/2020 3:01 PM
 * @topic round2.dp
 * @link
 * @description
 */
public class L279_PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
    @Test
    public void test() {
        System.out.println(numSquares(13));
    }
}
