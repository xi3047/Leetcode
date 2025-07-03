package round4.array;

public class L34_FirstAndLast {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[] {-1 , -1};
        int start = lowerBound(nums, target);
        if (start == nums.length || nums[start] != target) return new int[]{-1 , -1};
        int end = lowerBound(nums, target + 1) - 1;
        return new int[] {start, end};
    }

    private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
