package round2.array;

/**
 * @author Xi Zhang
 * @date 11/24/2020 8:32 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L238_ProductOfArrayCeptSelf {
    /**
     * Time O(n) Space O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 1;
        res[0] = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0)
                left = left * nums[i - 1];
            res[i] = left;
        }

        int right = 1;
        for (int i = n -1; i >= 0; i--) {
            if (i < n - 1)
                right = nums[i+ 1] * right;
            res[i] = res[i] * right;
        }
        return res;
    }
}
