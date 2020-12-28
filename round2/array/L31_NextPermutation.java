package round2.array;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/25/2020 3:27 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/next-permutation/
 * @description
 * mplement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.

 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 */
public class L31_NextPermutation {
    /** Solution (based on reverse backtracking)
     *  1. find the first non-descending integer from right to left
     *  2. swap the integer with the next greater integer from the right pool
     *  3. reverse the right pool so it is in ascending order
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int end = nums.length - 1;
            while (nums[end] <= nums[i]) end--;
            swap(nums, end, i);
        }
        reverse(nums, i + 1, nums.length -1);

    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }

    @Test
    public void test(){
        int[] nums = {1,2,3,4,5};
        nextPermutation(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }

    }
}
