package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/8/2020 10:29 PM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class L154_FindMinInRotatedSortedArray2 {
    /**
     * 只能使用模板二
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) right--;
            else if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        return nums[left];
    }
}
