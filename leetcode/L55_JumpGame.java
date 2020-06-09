package leetcode;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-15
    @time:   12:18
    Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
 */
public class L55_JumpGame {

    // Solution 1: Recursion
    // Idea: canJump(0) --> canJump(1) or canJump(2)
    // T(n): O(2^n), exponential growth, suppose k branches, k^n-1, **Time Limit Exceeded**
    // S(n): O(n), recursion requires additional memory for stack frames
    public boolean canJump(int[] nums) {
        return canJumpHelper(nums, 0);
    }

    private boolean canJumpHelper(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return true;
        }
        int furthestJump = index + nums[index];

        for (int nextPosition = index + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpHelper(nums, nextPosition)) {
                return true;
            }
        }
        return false;
    }

    // Solution 2: DP, bottom-up 从右往左，标记最后一个dp为true，在每个位置从左往右在当前跳跃范围之内有true的话就直接变true
    // Idea: dp[i] is true if there exists a j, such that i < j <= i + nums[i] and dp[j] is true
    // T(n): O (k*n)
    // S(n): O(n), can be reduced to O(1) in place by using -1 as true and -2 as false
    public boolean canJump2(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = i + 1; j <= nums[i] + i; j++) {
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }



    // Solution 3: Greedy
    // Idea: 从左往右看，每一步把最远能跳到的位置标记一下， 如果接下来的位置 在这个最大范围之内并且跳的还要更远，更新最远跳跃位置，
    // 如果最远跳跃位置等于最后一个位置或者出界了，返回true
    // T(n): O(n)
    // S(n): O(1)
    public boolean canJump4(int[] nums) {
        int furthestJump = 0;
        for (int i = 0; i < nums.length; i++) {
            if (furthestJump < i) return false;
            furthestJump = Math.max(furthestJump, i + nums[i]);
        }
        return true;
    }
    // greedy version 2
    public boolean canJump3V2(int[] nums) {
        int furthestJump = 0;
        for (int i = 0; i < nums.length && furthestJump >= i; i++) {  // or i <= furthestJump;
            if (furthestJump >= nums.length - 1) return true;
            furthestJump = Math.max(furthestJump, i + nums[i]);
        }
        return false;
    }



    @Test
    public void test() {
        int[] nums = {1, 2};
        System.out.println(canJump4(nums));

    }
}
