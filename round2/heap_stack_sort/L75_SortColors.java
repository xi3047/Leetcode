package round2.heap_stack_sort;

/**
 * @author Xi Zhang
 * @date 1/23/2021 1:12 PM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/sort-colors
 * @description
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * Example 3:
 *
 * Input: nums = [0]
 * Output: [0]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 */
public class L75_SortColors {
    /**
     * Dutch National Flag problem
     */
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
        }
    }
    private void swap (int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
