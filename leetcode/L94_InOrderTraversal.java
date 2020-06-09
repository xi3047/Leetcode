package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-03-02
    @time:   00:59
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class L94_InOrderTraversal {
    // recursive
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        inOrder(root.left);
        res.add(root.val);
        inOrder(root.right);
        return res;
    }

    // Iterative
    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
