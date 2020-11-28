package round2.tree;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 11/18/2020 11:07 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/kth-smallest-element-in-a-bst
 * @description Given a binary search tree, write a function kthSmallest to find the kth smallest
 * element in it.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3  6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 *
 *
 *
 *
 *
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the
 * kth smallest frequently? How would you optimize the kthSmallest routine?
 */
public class L230_KthSmallestBST {
    /**
     * Iterative solution using stack, In Order Traversal
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        int count = 0;

        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);  // Just like recursion
                p = p.left;

            } else {
                TreeNode node = stack.pop();
                if(++count == k) return node.val;
                p = node.right;
            }
        }

        return Integer.MIN_VALUE;
    }

    /**
     * In Order Traversal recursion
     */
    int count = 0;
    int res = 0;
    public int kthSmallestRecursion(TreeNode root, int k) {
        inOrder(root, k);
        return res;
    }
    private void inOrder(TreeNode root, int k) {
        if (root == null) return;
        inOrder(root.left, k);
        count++;
        if (count == k) {
            res = root.val;
            return;
        }
        inOrder(root.right, k);
    }
}
