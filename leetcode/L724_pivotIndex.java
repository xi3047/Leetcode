package leetcode;

import org.junit.Test;

public class L724_pivotIndex {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int total = 0;
        for (int i : nums) {
            total += i;
        }

        int leftSum  = 0;
        for (int i = 0; i < nums.length; i++) {
            leftSum += i == 0 ? 0 : nums[i - 1];
            int rightSum = 0;
            if ((total - nums[i]) % 2 == 0) {
                rightSum = (total - nums[i]) / 2;
            } else {
                continue;
            }
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = {-1, - 1 , -1 ,-1 , -1 , -1};
        System.out.println(pivotIndex(nums));
    }
}
