package round2.tree;

import leetcode.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/14/2020 10:56 PM
 * @topic round2.tree
 * @link
 */
public class L112_PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right ==null && root.val == sum) return true;
        return hasPathSum(root.left, sum - root.val)  || hasPathSum(root.right, sum - root.val);
    }
}
