package round4.bdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L90_SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);  // Step 1: Sort to detect duplicates
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));  // Add current subset

        for (int i = index; i < nums.length; i++) {
            // Step 2: Skip duplicates at the same level
            if (i > index && nums[i] == nums[i - 1]) continue;

            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        L90_SubsetsII subsets = new L90_SubsetsII();
        int[] nums = new int[]{ 2, 2, 2};
        System.out.println(subsets.subsetsWithDup(nums));
    }
}
