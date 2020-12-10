package round2.bdfs;

import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/9/2020 10:42 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/combination-sum-ii/
 * @description
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.

 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * Example 2:
 *
 * Input: candidates = [1,2,2,2,5], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 */
public class L40_CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    @Test
    public void test() {
        System.out.println(combinationSum2(new int[]{1,2,2,2,2,2,5}, 7));
    }
}
