package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-03-11
    @time:   19:55
 */
public class L124_maxPathSum {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return max;
    }

    private int maxSum(TreeNode root) {
        if (root == null) return 0;
        int left = maxSum(root.left);
        int right = maxSum(root.right);
        max = Math.max(max, left + root.val + right);
        return Math.max(0, root.val + Math.max(left, right));
    }

    public int maxPathSum2(TreeNode root) {
        // corner case
        if (root == null) {
            throw new IllegalArgumentException("Invalid tree");
        }
        int[] maxPath = new int[]{Integer.MIN_VALUE};
        dfs(root, maxPath);
        return maxPath[0];
    }

    // 以root为顶点的所有直上直下的path中path sum最大一条的值
    private int dfs(TreeNode root, int[] maxPath) {
        // base case: leaf node
        if (root == null) return 0;

        int left = Math.max(0, dfs(root.left, maxPath));
        int right = Math.max(0, dfs(root.right, maxPath));
        int cur = root.val + left + right;
        maxPath[0] = Math.max(maxPath[0], cur);
        return root.val + Math.max(left, right);
    }
}
