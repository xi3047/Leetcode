package round4.array;

public class L33_SearchInRotatedArray {
    public int search(int[] nums, int target) {
        int left = -1, right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // left is sorted
            if (nums[0] <= nums[mid]) {
                if (target < nums[mid]) {
                    right = mid;
                }
            }
        }
        return -1;
    }
}
