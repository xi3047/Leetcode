package leetcode;
/*
Time complexity: O(n*h)
 */
import java.util.Stack;

public class L653_twoSumBST {
    public boolean twoSumBST(TreeNode root, int target) {
        Stack<TreeNode> left = generateLeft(root); // h
        Stack<TreeNode> right = generateRight(root); // h

        while (!left.isEmpty() && !right.isEmpty()) {  // O(n)
            TreeNode l = left.peek();
            TreeNode r = right.peek();
            if (l == r) {
                return false;
            }
            if (l.val + r.val == target) {
                return true;
            } else if (l.val + r.val < target) {
                getNext(left);  // O(h)
            } else {
                getPrev(right);    // O(h)
            }
        }
        return false;
    }

    private Stack<TreeNode> generateLeft(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return stack;
    }

    private Stack<TreeNode> generateRight(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return stack;
    }

    private TreeNode getNext(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return top;
    }

    private TreeNode getPrev(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return top;
    }
}
