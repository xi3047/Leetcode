package round2.tree;

import round1.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 11/12/2020 10:15 PM
 * @topic round2.tree
 * @link
 */
public class L145_PostorderTraversal {
    /**
     * Recursive
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }

    /**
     * Iterative
     */
    public List<Integer> postorderIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(0,curr.val);
            if(curr.left!=null) {
                stack.push(curr.left);
            }
            if(curr.right!=null) {
                stack.push(curr.right);
            }
        }
        return list;

    }
}
