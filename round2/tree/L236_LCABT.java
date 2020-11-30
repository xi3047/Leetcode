package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/18/2020 4:19 PM
 * @topic round2.tree
 * @link
 * @description
 */
public class L236_LCABT {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null? left: right;
    }
}
