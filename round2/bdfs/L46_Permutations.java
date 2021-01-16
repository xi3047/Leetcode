package round2.bdfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/28/2020 8:23 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/permutations/
 * @description
 */
public class L46_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> path, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            dfs(res, path, nums);
            path.remove(path.size() - 1);
        }
    }


    // Solution 2: DFS with two branches
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> nums_list = new ArrayList<>();
        for (int number : nums) {
            nums_list.add(number);
        }
        backtrack2(nums_list, 0, res);
        return res;
    }
    private void backtrack2(List<Integer> nums, int position, List<List<Integer>> res) {
        if (position == nums.size() - 1) {
            res.add(new ArrayList<>(nums));
            return;
        }
        for (int i = position; i < nums.size(); i++) {
            Collections.swap(nums, i, position);
            backtrack2(nums, position + 1, res);
            Collections.swap(nums, i, position);
        }
    }
    private void swapInt(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        L46_Permutations test = new L46_Permutations();
        System.out.println(test.permute(new int[] {1, 2, 3}));
    }

}
