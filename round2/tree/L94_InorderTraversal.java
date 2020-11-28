package round2.tree;

import leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 11/12/2020 8:54 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 */
public class L94_InorderTraversal {
    /**
     * Recursive
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * Iterative
     */
    public List<Integer> inorderIterative(TreeNode root) {
        List<Integer> res = new LinkedList<>();

        // create an empty stack
        Stack<TreeNode> stack = new Stack();

        // start from root node (set current node to root node)
        TreeNode curr = root;

        // if current node is null and stack is also empty, we're done
        while (!stack.empty() || curr != null)
        {
            // if current node is not null, push it to the stack (defer it)
            // and move to its left child
            if (curr != null)
            {
                stack.push(curr);
                curr = curr.left;
            }
            else
            {
                // else if current node is null, we pop an element from stack,
                // print it and finally set current node to its right child
                curr = stack.pop();
                res.add(curr.val);

                curr = curr.right;
            }
        }
        return res;
    }
}
