package round2.array;

import java.util.Arrays;

/**
 * @author Xi Zhang
 * @date 11/27/2020 6:07 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L16_3SumClosest {
    public int threeSumClosest(int[] nums, int target) { if (nums == null || nums.length < 3);
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;  // Skip duplicates.

            // Reduce 3sum to 2sum.
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }

                if (target < sum) {
                    r--;
                } else if (target > sum) {
                    l++;
                } else {
                    return target;  // target == sum, we can't possibly get closer than that. The result is target.
                }
            }
        }
        return result;
    }
}
