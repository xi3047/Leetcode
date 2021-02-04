package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 1/28/21 11:30 下午
 * @topic round2.tree
 * @link https://leetcode.com/problems/univalued-binary-tree/
 * @description
 */
public class L965_UnivaluedBinaryTree {
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root, root.val);
    }

    private boolean dfs(TreeNode root, int val) {
        if (root == null) return true;
        if (root.val != val) return false;
        return dfs(root.left, val) && dfs(root.right, val);
    }
}
