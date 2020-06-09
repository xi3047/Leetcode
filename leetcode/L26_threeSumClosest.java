package leetcode;

import java.util.Arrays;

public class L26_threeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int start = i + 1;
            int end = nums.length -1;
            while (start < end) {
                int curSum = nums[start] + nums[end] + nums[i];
                if (curSum < target) {
                    start++;
                } else {
                    end--;
                }
                if (Math.abs(curSum - target) < Math.abs(res - target)) {
                    res = curSum;
                }
            }
        }
        return res;
    }
}
