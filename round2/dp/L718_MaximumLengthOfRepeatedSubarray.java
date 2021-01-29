package round2.dp;

import java.util.Arrays;

/**
 * @author Xi Zhang
 * @date 1/26/2021 2:00 PM
 * @topic round2.dp
 * @link https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 * @description
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 *
 * Example 1:
 *
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 *
 *
 * Note:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class L718_MaximumLengthOfRepeatedSubarray {
    /**
     * Top Down Recursion with Memoization
     */
    private class MaxLength {
        public int val = 0;
    }

    public int findLength(int[] A, int[] B) {
        MaxLength maxLength = new MaxLength();
        int[][] memo = new int[A.length][B.length];

        for (int row = 0; row < memo.length; row++) {
            Arrays.fill(memo[row], -1);
        }

        findLength(0, 0, A, B, maxLength, memo);
        return maxLength.val;
    }

    private int findLength(int i, int j, int[] A, int[] B, MaxLength maxLength, int[][] memo) {
        if (i >= A.length || j >= B.length) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int length = 0;

        if (A[i] == B[j]) {
            length = findLength(i + 1, j + 1, A, B, maxLength, memo) + 1;
            maxLength.val = Math.max(maxLength.val, length);
        }

        findLength(i + 1, j, A, B, maxLength, memo);
        findLength(i, j + 1, A, B, maxLength, memo);

        memo[i][j] = length;
        return length;
    }



    /**
     * Bottom UP DP
     */
    public int findLengthDP(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int max = 0;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(dp[i][j], max);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }
}
