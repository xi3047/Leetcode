package round2.array;

/**
 * @author Xi Zhang
 * @date 11/27/2020 11:39 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/first-missing-positive
 * @description https://www.youtube.com/watch?v=cG1rZPIo3ww&ab_channel=%E5%82%85%E7%A0%81%E7%88%B7
 */
public class L41_FirstMissingPositive {
    /*
    Solution explanation:
    [3, 4, -1, 1], the first missing positive number is 2
    The minimum is always 1, the maximum is always length
    We need a hash function to put the integers into their corresponding index in a sorted array from 1 to n.
    For example, 3 needs to be at index 2, 4 needs to be at index 3.
    After placing the wrongly positioned integers from 1 to n to their right positions, we go through the array
    and check the first number that is not correctly positioned.
    If all the numbers met the criteria, then there is no number missing from 1 to N, thus length + 1 is the answer.
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            /* the number that needs to be hashed need to meet the following criteria
                1. It needs to be in range of 1 to N
                2. It is not yet positioned correctly.
             Reason for while loop is we could swapped a smaller number into the current index and making
             it wrongly positioned, for example [-1, 4, 3, 1] and i = 1, after swapping 4 and 1, we will have
             [-1, 1, 3, 4], although 4 is correctly positioned but 1 is wrongly placed, we still need to swap
             it with -1, so the array becomes [1, -1, 3, 4], which is in correct order.
             */
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        // Check the first number that is not correctly placed
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
