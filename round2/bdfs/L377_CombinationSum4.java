package round2.bdfs;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Xi Zhang
 * @date 12/13/2020 12:10 PM
 * @topic round2.bdfs
 * @link
 * @description
 */
public class L377_CombinationSum4 {
    private int[] dp;

    public int combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }

    @Test
    public void test() {
        System.out.println(combinationSum4(new int[]{1,2,3},4));
    }
}
