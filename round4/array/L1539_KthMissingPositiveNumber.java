package round4.array;

public class L1539_KthMissingPositiveNumber {
    /** k = 5
     *            m
     *          r l
     *    0,1,2,3,4    index
     *    2,3,4,7,11   array
     *    1,2,3,4,5    order
     *    1 1 1 3 6    missing
     *    return 9
     *    1, 5, 6, 8, 9, 10
     *
     *
     *  r l
     *    7,8,9,10,11 return 5
     *    1,2,3,4 ,5
     *    arr[4] = 11, index 4, missing 6 numbers
     *
     *
     */

    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        int missing = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            missing = arr[mid] - (mid + 1);
            if (missing < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // arr[r] + k - missing
        // arr[r] + k - (arr[r] -r - 1)
        // arr[r] + k - arr[r] + r + 1
        // k + r + 1
        // k + l
        return k + left;
    }

    // linear
    public int findKthPostiveLinear(int[] nums, int k) {
        int cur = 1;
        int i = 0;
        while (k > 0) {
            if (i >= nums.length || nums[i] != cur) {
                k--;
            } else {
                i++;
            }
            cur++;
        }
        return cur - 1;
    }

    /** variant, takes 3 days to complete the project, asking first day this project can be completed
     *  k = 3 return 8
     *  l  m      r
     *  4, 7, 9, 10
        0  2  3  3
     missing 5, 6, 8, 11
     */

    public int findFirstDay(int[] nums, int k) {
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
