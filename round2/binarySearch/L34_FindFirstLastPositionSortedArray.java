package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/9/2020 12:07 AM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class L34_FindFirstLastPositionSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1,-1};
        if(nums == null || nums.length == 0) {
            return result;
        }
        int i = 0;
        int j = nums.length - 1;

        while(i <= j) {
            int mid = (i+j)/2;
            if(nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        if(i >= nums.length || nums[i] != target) {
            return result;
        } else {
            result[0] = i;
        }

        j = nums.length - 1;
        while(i <= j) {
            int mid = (i+j)/2 ;
            if(nums[mid] <= target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        result[1] = j;
        return result;
    }
}
