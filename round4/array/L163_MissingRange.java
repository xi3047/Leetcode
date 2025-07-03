package round4.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L163_MissingRange {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(Arrays.asList(lower, upper));
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] > lower) {
                    res.add(Arrays.asList(lower, nums[i] - 1));
                }
            } else if (nums[i] - nums[i - 1] > 1) {
                res.add(Arrays.asList(nums[i - 1] + 1, nums[i] - 1));
            }

            if (i == nums.length - 1) {
                if (nums[i]  < upper) {
                    res.add(Arrays.asList(nums[i] + 1, upper));
                }
            }

        }
        return res;
    }
}
