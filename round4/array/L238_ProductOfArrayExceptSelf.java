package round4.array;

public class L238_ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];

        prefix[0] = nums[0];
        for (int i = 1; i < n - 1; i++) {
            prefix[i] = nums[i] * prefix[i - 1];
        }

        suffix[n - 1] = nums[n - 1];
        for (int j = n -2; j >= 1; j--) {
            suffix[j] = nums[j] * suffix[j + 1];
        }
        int[] res = new int[n];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                res[i] = suffix[1];
            } else if (i == n - 1) {
                res[i] = prefix[n - 2];
            } else {
                res[i] = prefix[i - 1] * suffix[i + 1];
            }
        }
        return res;
    }
}
