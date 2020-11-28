package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/8/2020 12:41 PM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class L33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) return -1;

            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) return mid;
                else if (nums[left] < nums[mid]) {
                    if (nums[left] < target && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && target < nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return -1;
    }
}
