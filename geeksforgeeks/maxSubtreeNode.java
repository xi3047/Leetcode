package geeksforgeeks;

import leetcode.TreeNode;
import org.junit.Test;

public class maxSubtreeNode {
    public TreeNode maxSubtreeNode(TreeNode root) {
        int[] maxSum = new int[]{Integer.MIN_VALUE};
        TreeNode[] maxNode = new TreeNode[1];
        maxNode[0] = new TreeNode(root.val);
        dfs(root, maxSum, maxNode);
        return maxNode[0];
    }

    private int dfs(TreeNode root, int[] maxSum, TreeNode[] maxNode) {
        if (root == null) return 0;
        int curSum = root.val + dfs(root.left, maxSum, maxNode) + dfs(root.right, maxSum, maxNode);
        if (curSum > maxSum[0]) {
            maxSum[0] = curSum;
            maxNode[0] = root;
        }
        return curSum;
    }

    @Test
    public void test() {

        TreeNode root = TreeNode.createTree(new Integer[]{1, -2, 3, 6, 5, -6, 2});
        TreeNode.visualize(root);
        TreeNode maxNode = maxSubtreeNode(root);
        TreeNode.visualize(maxNode);

    }
}
