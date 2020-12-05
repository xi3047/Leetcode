package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/22/2020 11:36 PM
 * @topic round2.dp
 * @link https://leetcode.com/problems/maximum-subarray/
 * @description
 */
public class L53_MaxSubarray {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i -1] > 0 ? dp[i -1] + nums[i] : nums[i];
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
