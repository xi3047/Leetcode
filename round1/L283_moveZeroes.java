package round1;

import java.util.Arrays;

/*
    @author: Xi Zhang
    @date:   1/25/19
    @time:   12:11 PM

    Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

 */
public class L283_moveZeroes {
    public static void moveZeroes(int[] nums) {
        if (nums == null) return;
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                swap(nums, slow, fast);
                slow++;
            }
        }
    }
    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        System.out.print(Arrays.toString(nums));
    }
}
