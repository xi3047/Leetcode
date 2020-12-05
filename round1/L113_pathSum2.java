package round1;

import java.util.*;

public class L113_pathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        if (root == null) return res;
        dfs(res, root, sum, path);
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode root, int sum, List<Integer> path) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new LinkedList<>(path));
        }
        dfs(res, root.left, sum - root.val, path);
        dfs(res, root.right, sum - root.val, path);
        path.remove(path.size() - 1);

    }

    public static void main(String[] args) {

    }
}
