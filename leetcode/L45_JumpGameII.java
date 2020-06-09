package leetcode;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-15
    @time:   15:43

 Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
 */
public class L45_JumpGameII {
    // Solution 1: DP, bottom up
    // dp最后一个位置为0，dp[i】代表着从当前位置跳到结尾所用的最少次数
    // 从倒数第二位置往前，在当前位置跳跃范围内取 最小跳跃次数加一，如果无法跳到结尾，则为正无穷。
    // T(n): O(k*n)
    // S(n): O(n)
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            int minJump = Integer.MAX_VALUE;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    minJump = Math.min(minJump, dp[j + i]);
                }
            }
            dp[i] = minJump == Integer.MAX_VALUE? Integer.MAX_VALUE: minJump + 1;
        }
        return dp[0];
    }

    // Solution 2: Greedy
    // preMax 为上一次跳跃能达到最远的位置
    // curMax 为当前跳力能达到最远的位置
    public int jump2(int[] nums) {
        int times = 0;
        int preMax = 0;
        int curMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > preMax) {  // 当 当前位置i 大于上一次跳跃范围时，说明要再跳一次
                preMax = curMax;
                times++;
            }
            if (nums[i] + i >= nums.length -1 ) return times + 1;

            curMax = Math.max(curMax, nums[i] + i);
        }
        return times;
    }



    @Test
    public void test() {
        int[] nums = {2, 3, 1, 3, 2, 2, 0, 1};
        System.out.println(jump2(nums));

    }
}
