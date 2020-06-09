package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
    @author: Xi Zhang
    @date:   2019-04-25
    @time:   10:51

    Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.



Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 */
public class L523_ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null) return false;
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = sumToIndex.get(runningSum);
            if (prev != null) {
                if ( i - prev > 1) return true;
            } else {
                sumToIndex.put(runningSum, i);
            }
        }
        return false;
    }

    @Test
    public void test(){
        int[] nums = {23, 2, 6, 4};
        System.out.println(checkSubarraySum(nums, 6));

    }


}
