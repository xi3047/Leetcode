package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/6/2020 6:35 PM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/search-insert-position/
 */
public class L35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}
