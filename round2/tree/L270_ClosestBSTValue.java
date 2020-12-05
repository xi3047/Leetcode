package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/19/2020 6:39 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/closest-binary-search-tree-value/description/
 * @description
 */
public class L270_ClosestBSTValue {
    public int closestValue(TreeNode root, double target) {
        if (root == null) return root.val;
        TreeNode closest = root;
        TreeNode cur = root;
        while (cur != null) {
            if (Math.abs(cur.val - target) < (10^-10)) return cur.val;
            if (Math.abs(cur.val - target) < Math.abs(closest.val - target)) closest = cur;
            if (cur.val < target) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return closest.val;
    }
}
