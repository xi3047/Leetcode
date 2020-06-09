package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-03-03
    @time:   00:01
 */
public class L90_SubsetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfsHelper2(nums, res, new ArrayList<Integer>(), 0);
        return res;
    }
    private void dfsHelper(int[] nums, List<List<Integer>> res, List<Integer> level, int start) {
        res.add(new ArrayList<>(level));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue;
            level.add(nums[i]);
            dfsHelper(nums, res, level, i + 1);
            level.remove(level.size() - 1);
        }
    }

    private void dfsHelper2(int[] nums, List<List<Integer>> res, List<Integer> level, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(level));
            return;
        }
        level.add(nums[index]);
        dfsHelper2(nums, res, level, index + 1);
        level.remove(level.size() - 1);

        while (index < nums.length - 1 && nums[index] == nums[index + 1]) {
            index++;
        }
        dfsHelper2(nums, res, level, index + 1);
    }

    @Test
    public void test() {
        int[] nums = {1, 1, 2, 3};
        System.out.println(subsetsWithDup(nums));

    }
}
