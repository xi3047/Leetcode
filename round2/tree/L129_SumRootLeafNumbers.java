package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/16/2020 4:45 PM
 * @topic round2.tree
 * @link
 */
public class L129_SumRootLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return sumNodes(root, 0);
    }

    private int sumNodes(TreeNode root, int currentSum) {
        if (root == null) return 0;
        currentSum = currentSum * 10 + root.val;
        if (root.left == null && root.right == null) return currentSum;
        int leftSum = sumNodes(root.left, currentSum);
        int rightSum = sumNodes(root.right, currentSum);
        return leftSum + rightSum;
    }
}
