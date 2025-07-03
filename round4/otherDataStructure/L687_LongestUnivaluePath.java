package round4.otherDataStructure;

import round1.TreeNode;

public class L687_LongestUnivaluePath {
    int longest;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return longest;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);

        int leftPath = 0, rightPath = 0;
        if (root.left != null && root.left.val == root.val) {
            leftPath += left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            rightPath += right + 1;
        }

        longest = Math.max(longest, leftPath + rightPath);

        return Math.max(leftPath, rightPath);

    }
}
