package round1;

import org.junit.Test;

import java.util.*;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.


 */
public class L300_LongestIncrSubsequence {
    // Solution 1: 循环一遍，用binary search找到比每个数字大的最小值，
    public int lengthOfLIS(int[] nums) {
        if (nums == null) return 0;
        List<Integer> res = new ArrayList<>();
        for (int n : nums) {
            if (res.size() == 0) {
                res.add(n);
            } else {
                if (res.get(res.size() - 1) < n) {
                    res.add(n);
                } else {
                    updateResult(res, n);
                }
            }
        }
        return res.size();
    }

    private void updateResult(List<Integer> res, int n) {
        int start = 0;
        int end = res.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midValue = res.get(mid);
            if (midValue == n) return;
            else if (midValue < n) start = mid + 1;
            else {
                end = mid - 1;
            }
        }
        res.set(start, n);
    }

    // Solution 2: Dynamic programming O(n^2)
    public int lengthOfLIS2(int[] nums) {
        if(nums.length <= 1)return nums.length;

        int dp[] = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i=1; i < nums.length; i++) {
            for(int j=0; j < i; j++) {
                // It means next number contributes to increasing sequence.
                if(nums[j] < nums[i]) {
                    // But increase the value only if it results in a larger value of the sequence than T[i]
                    // It is possible that T[i] already has larger value from some previous j'th iteration
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }

        // Find the maximum length from the array that we just generated
        int longest = 0;
        for(int i=0; i < dp.length; i++)
            longest = Math.max(longest, dp[i]);

        return longest;
    }

    // Similar question, Length of longest Increasing SubArray
    public int lengthOfLongestIncreasingSubarray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = nums[i] > nums[i - 1] ? dp[i - 1] + 1 : 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    @Test
    public void test() {
        int[] nums = {3, 1, 5, 2, 6, 4, 9, 10, 7};
        System.out.println(lengthOfLIS(nums));
    }
}
