package round2.dp;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/27/2020 10:32 PM
 * @topic round2.dp
 * @link
 * @description
 */
public class L55_JumpGame {
    /**
     * Solution 1:
     * Recursion with memoization
     */
    Boolean[] memo;
    public boolean canJumpRecursion(int[] nums) {
        memo = new Boolean[nums.length];
        memo[nums.length -1] = true;
        return canJumpFromPosition(0, nums);
    }
    public boolean canJumpFromPosition(int position, int[] nums) {

        if (memo[position] != null) {
            return memo[position];
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                memo[position] = true;
                return true;
            }
        }
        memo[position] = false;
        return false;
    }

    /**
     * Dynamic Programming
     */
    public boolean canJumpDP(int[] nums) {
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int furthest = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthest; j++) {
                if (dp[j] == 1) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[0] == 1;
    }

    /**
     * Greedy
     */
    public boolean canJump(int[] nums) {
        int lastGoodPosition = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastGoodPosition) {
                lastGoodPosition = i;
            }
        }
        return lastGoodPosition == 0;
    }

    @Test
    public void test() {
        int[] nums = {2,3,1,1,4};
        System.out.println(canJumpDP(nums));
    }
}
