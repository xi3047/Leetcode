package round2.tree;

import leetcode.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/13/2020 5:38 PM
 * @topic round2.tree
 * @link
 */
public class L104_MaxDepthBinaryTree {
    /**
     * DFS
     */
    public int maxDepth(TreeNode root) {
        int [] maxHeight = new int[] {0};
        dfs(root, 0, maxHeight);
        return maxHeight[0];
    }

    private void dfs(TreeNode root, int height, int[]  maxHeight) {
        if (root == null) return;
        maxHeight[0] = Math.max(height + 1, maxHeight[0]);
        dfs(root.left, height + 1, maxHeight);
        dfs(root.right, height + 1, maxHeight);
    }

    /**
     *  Easy Solution
     */
    public int maxDepth2(TreeNode root) {
        if(root==null){
            return 0;
        }
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
