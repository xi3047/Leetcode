package round2.tree;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 11/19/2020 4:27 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/binary-search-tree-iterator/
 * @description
 */
public class L173_BSTIterator {
    Stack<TreeNode> stack;
    public L173_BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return top.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }


}
