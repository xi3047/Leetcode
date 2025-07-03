package round4.array;

public class L1060_MissingElementInSortedArray {
    public int missingElement(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int missing = 0;
        int start = nums[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            missing = nums[mid] - mid - start;
            if (missing < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // arr[r] + k - missing
        // arr[r] + k - arr[r] + r + arr[0]
        // k + r + arr[0]
        // 2 + 2 + 4
        return nums[0] + k + right;
    }
}
