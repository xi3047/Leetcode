package round2.bdfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/9/2020 8:38 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/combination-sum/
 * @description
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 * Example 4:
 *
 * Input: candidates = [1], target = 1
 * Output: [[1]]
 * Example 5:
 *
 * Input: candidates = [1], target = 2
 * Output: [[1,1]]
 */
public class L39_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(candidates, target, res, path, 0, 0);
        return res;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int curSum, int start) {
        if (curSum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (curSum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrack(candidates, target, res, path , curSum + candidates[i], i);
            path.remove(path.size() - 1);
        }
    }
    @Test
    public void test() {
        System.out.println(combinationSum(new int[]{2,3,6,7}, 7));
    }
}
