package round2.tree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/18/2020 1:22 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/validate-binary-search-tree/
 * @description Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class L98_ValidBinarySearchTree {
    /**
     * Solution 1, use two bounds and recurse down
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    private boolean isValidBST(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) return true;
        if ((lowerBound == null || root.val > lowerBound) && (upperBound == null || root.val < upperBound)) {
            return isValidBST(root.left, lowerBound, root.val) && isValidBST(root.right, root.val, upperBound);
        } else {
            return false;
        }
    }

    /**
     * Solution 2: extract list with in order traversal, then compare each number from left to right
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        List<Integer> list = new ArrayList<>();
        helper(list, root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) <= list.get(i)) return false;
        }
        return true;
    }

    private void helper(List<Integer> list, TreeNode root) {
        if (root.left != null) helper(list, root.left);
        list.add(root.val);
        if (root.right != null) helper(list, root.right);
        return;
    }
}
