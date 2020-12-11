package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 12/8/2020 2:09 PM
 * @topic round2.tree
 * @link
 * @description
 */
public class L671_SecondMaxNodeBinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
        // return -1 when the current node is the same as root
        // or when this node has no children
        if (root == null || root.left == null) return -1;
        int left = root.left.val;
        int right = root.right.val;
        if (root.val == root.left.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (root.val == root.right.val) {
            right = findSecondMinimumValue(root.right);
        }
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else return left == -1 ? right : left;

    }
}
