package leetcode;


/*
    @author: Xi Zhang
    @date:   2019-04-04
    @time:   23:02
    Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 */
public class L298_BTLongestSequence {
    private int maxLength;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        dfs(root, null, 0);
        return maxLength;
    }

    private void dfs(TreeNode root, TreeNode parent, int length) {
        if (root == null) return;
        length = parent != null && root.val == parent.val + 1 ? length + 1: 1;
        maxLength = Math.max(length, maxLength);
        dfs(root.left, root, length);
        dfs(root.right, root, length);
    }

}
