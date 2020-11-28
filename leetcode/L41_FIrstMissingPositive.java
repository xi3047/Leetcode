package leetcode;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-05-09
    @time:   13:16

    Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3

Example 2:
Input: [3,4,-1,1]
Output: 2

Example 3:
Input: [7,8,9,11,12]
Output: 1
Note:
Your algorithm should run in O(n) time and uses constant extra space.


 */
public class L41_FIrstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        //corner case
        if (nums == null || nums.length == 0) return 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1 && nums[i] > 0 && nums[i] <= nums.length) {
                if (nums[nums[i] - 1] == nums[i]) continue;//排除掉负数的部分

                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
                i--;
            }
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != j + 1) return j + 1;
            }
        }
        return nums.length + 1;

    }

    public int firstMissingPositive2(int[] nums) {
        int len = nums.length;

        if (nums[0] == 1 && len == 1) return 2;
        boolean contains1 = false;
        boolean containsLenNumber = false;
        // preprocessing - get rid of non-positive numbers, numbers > len
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) contains1 = true;
            if (nums[i] == len) containsLenNumber = true;
            if ((nums[i] <= 0) || (nums[i] >= len))
                nums[i] = 1;
        }

        if (!contains1) return 1;

        // treat the nums array as a boolean array where element is all possible positive number and their presence
        // go through the array, mark boolean[ nums[i] ] negative if present, note, only change to negative once
        for (int i = 0; i < len; i++) {
            int a = Math.abs(nums[i]);
            // If you meet number a in the array - change the sign of a-th element.
            // Be careful with duplicates : do it only once.
            nums[a] = - Math.abs(nums[a]);
        }

        // go through the array second time, the first idx that is negative is our answer
        for (int i = 1; i < len; i++) {
            if (nums[i] > 0)
                return i;
        }
        return containsLenNumber ? len + 1: len;
    }

    @Test
    public void test() {
        int[] test = {3, 4, -1 ,1 , -2};
        System.out.println(firstMissingPositive2(test));

    }



}
