package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/19/2020 11:28 AM
 * @topic round2.tree
 * @link https://leetcode.com/problems/inorder-successor-in-bst/
 * @description
 */
public class L285_InOrderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return helper(root, p, null);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode successor) {
        if (root == null) return root;
        if (root.val == p.val) {
            if (root.right != null) {
                return findMin(root.right);
            } else {
                return successor;
            }
        } else if (root.val > p.val) {
            successor = root;
            return helper(root.left, p, successor);
        } else {
            return helper(root.right, p, successor);
        }
    }

    private TreeNode findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
