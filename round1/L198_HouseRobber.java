package round1;

import org.junit.Test;

import java.util.Arrays;

/*
    @author: Xi Zhang
    @date:   2019-03-13
    @time:   01:03

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class L198_HouseRobber {
    // Solution 1: Recursion (Top-Down) Time Limit Exceeded 2^n
    public int rob(int[] nums) {
        return rob(nums, nums.length - 1);
    }
    private int rob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
    }

    // Solution 2: Recursive + memo (top-down), Time O(n), Space O(n)
    int[] memo;
    public int rob2(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return rob(nums, nums.length - 1);
    }

    private int rob2(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] >= 0) {
            return memo[i];
        }
        int result = Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
        memo[i] = result;
        return result;
    }

    // Solution 3: Iterative + memo (bottom up)
    public int rob3(int[] nums) {
        if (nums.length == 0) return 0;
        int[] memo = new int[nums.length];
        memo[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int prev = 0;
            if (i != 1) {
                prev = memo[i - 2];
            }
            memo[i] = Math.max(nums[i] + prev, memo[i-1]);
        }
        return memo[nums.length - 1];
    }

    // Solution 4: Iterative + two variables (bottom-up)
    /* the order is: prev2, prev1, num  */
    public int rob4(int[] nums) {
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }


    @Test
    public void test (){
        int[] nums = {2, 1, 1, 2};
        System.out.println(rob3(nums));
    }


}
