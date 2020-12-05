package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/8/2020 8:01 PM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 */
public class L81_SearchInRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                return true;
            }
            //左半段是有序的
            if (nums[start] < nums[mid]) {
                //target 在这段里
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                //右半段是有序的
            } else if (nums[start] > nums[mid]){
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else { // 如果头与中相等，说明是重复数字，往右移动左标直到
                start ++;
            }

        }
        return false;
    }
}
