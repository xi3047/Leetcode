package round1;
/*
    @author: Xi Zhang
    @date:   2019-03-03
    @time:   00:13
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */
import org.junit.Test;

import java.util.*;

public class L47_PermutationII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        }
        int previous = nums[0] - 1; // initialize previous with a number that is not present in the given array
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == false && nums[i] != previous) {
                previous = nums[i];
                tempList.add(nums[i]);
                used[i] = true;
                backtrack(list, tempList, nums, used);
                tempList.remove(tempList.size() - 1);
                used[i] = false;
            }
        }
    }

    // Solution 2: use HashSet to de-duplicate
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int num : nums) {
            temp.add(num);
        }
        backtrack2(temp, 0, res);
        return res;
    }
    private void backtrack2(List<Integer> nums, int index, List<List<Integer>> res) {
        if (index == nums.size() - 1) {
            res.add(new ArrayList<>(nums));
            return;
        }
        Set<Integer> appeared = new HashSet<>();
        for (int i = index; i < nums.size(); i++) {
            if(appeared.add(nums.get(i))) {
                Collections.swap(nums, i, index);
                backtrack2(nums, index + 1, res);
                Collections.swap(nums, i, index);
            }
        }
    }
    private void swapInt(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    @Test
    public void test() {
        int[] nums = {1, 2, 2};
        //System.out.println(permuteUnique(nums));
        System.out.println(permute2(nums));
        //Assert.assertEquals(permute2(nums), permuteUnique(nums));

    }
}
