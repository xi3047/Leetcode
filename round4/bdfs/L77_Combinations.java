package round4.bdfs;

import java.util.ArrayList;
import java.util.List;

public class L77_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(res, new ArrayList<>(), n, k, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> path, int n, int k, int index) {
         if (path.size() == k) {
             res.add(new ArrayList<>(path));
             return;
         }

         for (int i = index; i < n; i++) {
             path.add(i);
             dfs(res, path, n, k, i + 1);
             path.remove(path.size() - 1);
         }
    }

}
