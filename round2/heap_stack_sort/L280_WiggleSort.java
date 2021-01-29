package round2.heap_stack_sort;

/**
 * @author Xi Zhang
 * @date 1/20/2021 4:32 PM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/wiggle-sort/
 * @description
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * Example:
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]

 */
public class L280_WiggleSort {
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0 && nums[i] > nums[i + 1]) {
                swap(nums, i, i + 1);
            }
            if (i % 2 == 1 && nums[i] < nums[i + 1]) {
                swap(nums, i, i + 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
