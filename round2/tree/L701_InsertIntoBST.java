package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 1/12/21 10:30 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/insert-into-a-binary-search-tree/
 * @description
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node
 * of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion.
 * You can return any of them.
 *
 * Example 1:
 *        4
 *       / \
 *      2   7
 *     / \
 *    1   3
 *
 *   Insert 5
 *         4
 *       /   \
 *      2     7
 *     / \   /
 *    1  3  5
 */
public class L701_InsertIntoBST {
    /**
     * Recursive
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    /**
     * Iterative
     */
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        TreeNode cur = root;
        while(true) {
            if(cur.val <= val) {
                if(cur.right != null) cur = cur.right;
                else {
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                if(cur.left != null) cur = cur.left;
                else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
