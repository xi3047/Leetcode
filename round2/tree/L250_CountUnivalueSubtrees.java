package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 01/12/2021 9:52 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/count-univalue-subtrees/
 */
public class L250_CountUnivalueSubtrees {

    int count;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        isUnivalSubtree(root);
        return count;
    }

    // return if current subtree is a univalue subtree
    private boolean isUnivalSubtree(TreeNode root) {
        if (root == null) return true;
        boolean left = isUnivalSubtree(root.left);
        boolean right = isUnivalSubtree(root.right);
        if (left && right) {
            if ((root.left == null || root.val == root.left.val) && (root.right == null || root.val == root.right.val)) {
                count++;
                return true;
            }
        }
        return false;
    }
}
