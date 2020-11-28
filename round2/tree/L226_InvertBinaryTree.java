package round2.tree;

import leetcode.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/14/2020 7:58 PM
 * @topic round2.tree
 * @link
 */
public class L226_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }
}
