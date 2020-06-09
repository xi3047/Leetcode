package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-05-30
    @time:   21:49
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?
 */
public class L154_FindMinimumInRotateSortedArrayII {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int rotateIndex = getIndex(nums);
        return nums[rotateIndex];
    }

    private int getIndex(int[] nums) {
        // already sorted
        if (nums[0] <= nums[nums.length - 1]) return 0;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid != 0 && nums[mid] < nums[mid - 1]) return mid;
            else if (nums[mid] > nums[right]) left = mid + 1;
            else if (nums[mid] < nums[right]) right = mid - 1;
            else right--;
        }
        return 0;
    }
}
