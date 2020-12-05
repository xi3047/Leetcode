package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/17/2020 6:10 PM
 * @topic round2.tree
 * @link
 */
public class L250_CountUnivalueSubtrees {

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        int[] count = new int[1];
        isUnivalSubtree(root, count);
        return count[0];
    }

    // return if current subtree is a univalue subtree
    private boolean isUnivalSubtree(TreeNode root, int[] count) {
        if (root == null) return true;
        boolean left = isUnivalSubtree(root.left, count);
        boolean right = isUnivalSubtree(root.right, count);
        if ((left && right) && (root.left == null || root.left.val == root.val) && (root.right == null || root.right.val == root.val)) {
            count[0]++;
            return true;
        }
        return false;
    }
}
