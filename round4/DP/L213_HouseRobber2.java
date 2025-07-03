package round4.DP;

public class L213_HouseRobber2 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        return Math.max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
    }

    private int robHelper(int[] nums, int start, int end) {
        if (start == end) return nums[start];

        int prev2 = nums[start];
        int prev1 = Math.max(prev2, nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}
