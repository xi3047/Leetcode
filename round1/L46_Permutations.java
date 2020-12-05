package round1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-03-03
    @time:   00:02
 */
public class L46_Permutations {
    // Solution 1: dfs with one branch
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        }
        for(int i = 0; i < nums.length; i++){
            if(tempList.contains(nums[i])) continue; // element already exists, skip
            tempList.add(nums[i]);
            backtrack(list, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
    }

    // Solution 2: DFS with two branches
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack2(nums, 0, res);
        return res;
    }
    private void backtrack2(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length - 1) {
            List<Integer> temp = new ArrayList<>();
            for (int i : nums) {
                temp.add(i);
            }
            res.add(temp);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swapInt(nums, i, index);
            backtrack2(nums, index + 1, res);
            swapInt(nums, i, index);
        }
    }

    private void swapInt(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // DFS with two branches on character input
    public List<String> permuteString(char[] array) {
        List<String> res = new ArrayList<>();
        dfs(array, 0, res);
        return res;
    }

    private void dfs(char[] array, int index, List<String> res) {
        if (index == array.length - 1) {
            res.add(new String(array));
            return;
        }
        for (int i = index; i < array.length; i++) {
            swap(array, i, index);
            dfs(array, index + 1, res);
            swap(array, i, index);
        }
    }

    private void swap(char[] array, int i, int index) {
        char temp = array[i];
        array[i] = array[index];
        array[index] = temp;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }

    @Test
    public void test2() {
        int[] nums = {1, 2, 3};
        System.out.println(permute2(nums));

    }

    @Test
    public void test3() {
        char[] arr = {'a', 'b', 'c'};
        List<String> res = permuteString(arr);
        int counter = 1;
        for (String s : res) {
            System.out.println(counter + ":" + s);
            counter++;
        }
    }
}
