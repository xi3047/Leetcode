package round4.otherDataStructure;

import round1.TreeNode;

public class L129_SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (node == null) return 0;
        currentSum = currentSum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return currentSum;
        }
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
