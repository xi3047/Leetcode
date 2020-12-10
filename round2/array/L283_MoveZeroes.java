package round2.array;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 11/30/2020 3:42 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/move-zeroes/
 * @description
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 *        [1,0,0,3,12]
 *        [
 *
 *
 *
 * Output: [1,3,12,0,0]
 */
public class L283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, j);
                j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int copyI = nums[i];
        nums[i] = nums[j];
        nums[j] = copyI;
    }

    @Test
    public void test() {
        moveZeroes(new int[] {0,1,0,3,12});
    }

}
