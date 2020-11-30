package round1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-03-05
    @time:   23:07

Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
 */

/**
 * Solution is incorrect, as it counts different sequence as same combination.
 */
public class L377_CombinationSumIV {
    public List<List<Integer>> combinationSum4(int[] nums, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, k, 0, res, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int[] nums, int k, int start, List<List<Integer>> res, List<Integer> temp) {
        if (k < 0) return;
        if (k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums, k - nums[i], i, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println(combinationSum4(nums, 4));
    }

}
