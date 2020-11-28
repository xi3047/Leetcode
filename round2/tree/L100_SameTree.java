package round2.tree;

import leetcode.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/14/2020 1:40 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/same-tree/
 */
public class L100_SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
