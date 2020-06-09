package leetcode;

import java.util.NoSuchElementException;
import java.util.Stack;

public class L173_BST_Iterator {

    Stack<TreeNode> stack;
    public L173_BST_Iterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return top.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
