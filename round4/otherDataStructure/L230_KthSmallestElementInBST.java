package round4.otherDataStructure;

import round1.TreeNode;

import java.util.Stack;

public class L230_KthSmallestElementInBST {
    /**
     * Using stack
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }

    /**
     * Using in order
     */
    private int ans;
    private int count;
    public int kthSmallest2(TreeNode root, int k) {
        inOrder(root, k);
        return ans;
    }
    private void inOrder(TreeNode root, int k) {
        if (root == null) return;
        inOrder(root.left, k);
        if (++ count == k) {
            ans = root.val;
            return;
        }
        inOrder(root.right, k);
    }


}
