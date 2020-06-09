package leetcode;

import java.util.ArrayList;
import java.util.List;

public class L366_LeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    private int height(TreeNode root, List<List<Integer>> res) {
        if (root == null) return -1;
        int height = Math.max(height(root.left, res), height(root.right, res)) + 1;
        if (res.size() == height) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        root.left = root.right = null;
        return height;
    }


}
