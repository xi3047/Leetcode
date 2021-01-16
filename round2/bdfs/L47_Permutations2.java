package round2.bdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Xi Zhang
 * @date 1/15/21 3:59 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/permutations-ii/
 * @description
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in
 * any order.
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class L47_Permutations2 {
    /**
     * Since we only need permutations of the array, the actual "content" does not change, we could find each permutation
     * by swapping the elements in the array.
     * The idea is for each recursion level, swap the current element at 1st index with each element that comes after it
     * (including itself). For example, permute[1,2,3]:
     * At recursion level 0, current element at 1st index is 1, there are 3 possibilities:
     * [1] + permute[2,3], [2] + permute[1,3], [3] + permute[2,1].
     * Take "2+permute[1,3]" as the example at recursion level 0. At recursion level 1, current elemenet at
     * 1st index is 1, there are 2 possibilities: [2,1] + permute[3], [2,3] + permute[1].
     * ... and so on.
     *
     * Let's look at another example, permute[1,2,3,4,1].
     * At recursion level 0, we have [1] + permute[2,3,4,1], [2] + permute[1,3,4,1], [3] + permute[2,1,4,1],
     * [4] + permute[2,3,1,1], [1] + permute[2,3,4,1].
     * 1 has already been at the 1st index of current recursion level, so the last possibility is redundant.
     * We can use a hash set to mark which elements have been at the 1st index of current recursion level, so that if
     * we meet the element again, we can just skip it.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums==null || nums.length==0) return res;
        Arrays.sort(nums);
        permute(nums, res, 0);
        return res;
    }

    private void permute(int[] nums,List<List<Integer>> res, int start) {
        if (start == nums.length - 1) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) {
                temp.add(num);
            }
            res.add(temp);
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (set.add(nums[i])) {
                swap(nums, i, start);
                permute(nums, res, start + 1);
                swap(nums, i, start);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int save = nums[i];
        nums[i] = nums[j];
        nums[j] = save;
    }
}
