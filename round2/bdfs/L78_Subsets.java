package round2.bdfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/27/2020 5:35 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/subsets/
 * @description Given an integer array nums, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 */
public class L78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;
        backtrack(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(int[] nums, List<List<Integer>> res, ArrayList<Integer> path, int start) {
        if (start == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        path.add(nums[start]);
        backtrack(nums, res, path, start+ 1);
        path.remove(path.size() - 1);
        backtrack(nums, res, path, start+ 1);

    }
    @Test
    public void test() {
        System.out.println(subsets(new int[] {1,2,3}));
    }
}
