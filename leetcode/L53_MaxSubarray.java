package leetcode;

import org.junit.Test;

import java.util.Arrays;

/*
    @author: Xi Zhang
    @date:   2019-02-15
    @time:   13:39

    Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

    Example:

    Input: [-2,1,-3,4,-1,2,1,-5,4],
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class L53_MaxSubarray {

    /*
        Solution: Kadane's algorithm
        Space O(1)

     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int maxCur = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxCur = Math.max(nums[i], maxCur + nums[i]);
            max = Math.max(max, maxCur);
        }
        return max;
    }



    // Solution 2: using dynamic programming, Space O(n)
    public int maxSubArray2(int[] nums) {
        if (nums.length  == 1) return nums[0];
        int max = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int curLeft = 0, left = 0, right = 0;
        for (int i = 1; i < dp.length; i++) {
            if (nums[i] + dp[i - 1] > nums[i]) { // dp[i - 1] > 0                                                                                                                                                                     
                dp[i] = nums[i] + dp[i - 1];
            } else {
                dp[i] = nums[i];
                left = i;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int[] maxSubArray3(int[] array) {
        int[] index = new int[]{-1, -1};
        if (array == null || array.length == 0) {
            return index;
        }
        int res = array[0];
        int cur = array[0];
        int gLeft = 0;
        int gRight = 0;
        int left = 0;

        for (int i = 1; i < array.length; i++)
        {
            // 如果贡献为负数的话，就重新定一个window, 把左边界设为当前的位置
            if (cur < 0) {
                cur = array[i];
                left = i;
            } else {
                cur += array[i];
            }
            // 如果新的sum值更大的话，拉长当前的window，把右边界设为当前的位置
            if (cur > res) {
                res = cur;
                gLeft = left;
                gRight = i;
            }
        }
        index[0] = gLeft;
        index[1] = gRight;
        return Arrays.copyOfRange(array, gLeft, gRight + 1);
    }

    @Test
    public void test() {
        int[] nums = {-1, -2 , -3};
        int[] indices = maxSubArray3(nums);
        for (int i : indices) {
            System.out.print(i + " ");
        }
    }
}
