package round2.tree;

import leetcode.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/16/2020 9:45 PM
 * @topic round2.tree
 * @link
 */
public class L110_BalancedBinaryTree {
    boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        maxDepth(root);
        return ans;
    }

    private int maxDepth(TreeNode root) {
        if (root == null || !ans) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (Math.abs(left - right) > 1) ans = false;
        return 1 + Math.max(left, right);
    }
}
