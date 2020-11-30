package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-12
    @time:   21:13
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class L152_MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        Tuple[] dp = new Tuple[nums.length];
        dp[0] = new Tuple(nums[0],nums[0]);
        int res = dp[0].max;
        for (int i = 1; i < nums.length; i++) {
            Tuple prev = dp[i - 1];
            int imax = Math.max(Math.max(nums[i], nums[i] * prev.max) , nums[i] * prev.min);
            int imin = Math.min(Math.min(nums[i], nums[i] * prev.max) , nums[i] * prev.min);
            dp[i] = new Tuple(imax,imin);
            res = Math.max(imax, res);
        }
        return res;
    }

    class Tuple {
        private int max;
        private int min;

        private Tuple(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    @Test
    public void test() {
        int[] nums = {2,3,-2,-3};
        System.out.println(maxProduct(nums));
    }
}
