package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 12/12/2020 11:32 PM
 * @topic round2.tree
 * @link
 * @description
 */
public class L156_BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}