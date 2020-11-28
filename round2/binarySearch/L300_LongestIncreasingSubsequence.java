package round2.binarySearch;

import java.util.Arrays;

/**
 * @author Xi Zhang
 * @date 11/9/2020 10:22 PM
 * @topic round2.binarySearch
 * @link
 */
public class L300_LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int longest = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        longest = Math.max(longest, dp[i]);
                    }
                }
            }
        }
        return longest;
    }
}
