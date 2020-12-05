package round1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-03-04
    @time:   18:39
    Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */
public class L77_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, 1, res, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int n, int k, int start, List<List<Integer>> res, List<Integer> temp) {
        if (k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            dfs(n, k - 1,i + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(combine(4, 2));
    }
}
