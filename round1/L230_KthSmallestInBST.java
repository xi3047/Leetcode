package round1;

import java.util.Stack;

/*
    @author: Xi Zhang
    @date:   2019-03-02
    @time:   10:13
 */
public class L230_KthSmallestInBST {
    // Solution 1: using iterative in order traversal
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (--k == 0) break;
            cur = cur.right;
        }
        return cur.val;
    }

    // Solution 2: recursive, (discouraged for using because of global variable)
    int count = 0;
    int result = Integer.MIN_VALUE;

    public int kthSmallest2(TreeNode root, int k) {
        traverse(root, k);
        return result;
    }

    public void traverse(TreeNode root, int k) {
        if(root == null) return;
        traverse(root.left, k);
        count ++;
        if(count == k) {
            result = root.val;
            return;
        }
        traverse(root.right, k);
    }

    public int traverse2(TreeNode root, int k) {
        if(root == null) return -1;
        traverse(root.left, k);
        if(++count == k) {
            return root.val;
        }
        traverse(root.right, k);
        return -1;
    }
}
