package round2.tree;

import org.junit.Test;
import round1.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 11/12/2020 10:16 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/binary-tree-level-order-traversal
 */
public class L102_LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.add(cur);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            while (size-- > 0) {
                cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(level);
        }
        return res;
    }

    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        if (res.size() == level) {
            res.add(level, new LinkedList<>());
        }
        res.get(level).add(root.val);
        dfs(res, root.left, level + 1);
        dfs(res, root.right, level + 1);
    }

    @Test
    public void test() {
        List<List<Integer>> res = new LinkedList<>();
        System.out.println(res.size() == 0);
        res.add(0, new LinkedList<>());
        System.out.println(res.size());
    }
}
