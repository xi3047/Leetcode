package round1;

/*
    @author: Xi Zhang
    @date:   2019-05-30
    @time:   21:38

    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2]
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0

 */
public class L153_FindMinimumInRotatedSortedArray {
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
        }
        return 0;
    }
}
