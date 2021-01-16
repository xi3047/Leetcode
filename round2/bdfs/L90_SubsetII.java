package round2.bdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/15/21 7:18 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/subsets-ii/
 * @description
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class L90_SubsetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        subset(res, nums, 0, new ArrayList<Integer>());
        return res;
    }

    private void subset(List<List<Integer>> res, int[] nums, int start, ArrayList<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            subset(res, nums, i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}
