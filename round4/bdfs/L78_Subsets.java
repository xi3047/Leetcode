package round4.bdfs;

import java.util.ArrayList;
import java.util.List;

public class L78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> path, int[] nums, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        path.add(nums[index]);
        dfs(res, path, nums, index + 1);
        path.remove(path.size() - 1);

        dfs(res, path, nums, index + 1);
    }

    private void dfs2(List<List<Integer>> res, ArrayList<Integer> path, int[] nums, int index) {
        // Add current subset to result
        res.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i++) {
            // Include nums[i]
            path.add(nums[i]);

            // Move to the next element
            dfs(res, path, nums, i + 1);

            // Backtrack (remove last element)
            path.remove(path.size() - 1);
        }
    }
}
