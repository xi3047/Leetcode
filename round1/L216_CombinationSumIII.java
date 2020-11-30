package round1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-03-05
    @time:   23:01
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class L216_CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), k, 1, n);
        return res;

    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> temp, int k, int start, int n) {
        if (temp.size() == k && n == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= 9; i++) {
            temp.add(i);
            dfs(res, temp, k, i + 1, n - i);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test (){
        System.out.println(combinationSum3(3, 9));
    }


}
