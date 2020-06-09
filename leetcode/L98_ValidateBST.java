package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
    @author: Xi Zhang
    @date:   2/12/19
    @time:   1:58 PM
 */
public class L98_ValidateBST {

    // Solution 0
    // corner case fails when node is Int.MIN or MAX
    public boolean isValidBST3(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST3(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) return true;
        if (root.val <= lowerBound || root.val >= upperBound) return false;
        return isValidBST(root.left, lowerBound, root.val ) && isValidBST(root.right, root.val, upperBound);
    }

    // Solution 1: using a lower bound and upper bound recursively
    // if we use Integer.MIN_VALUE and MAX_VALUE as bounds, but it will fail with a node of MIN and MAX
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) return true;
        if ((lowerBound != null && root.val <= lowerBound) || (upperBound != null && root.val >= upperBound)) return false;
        return isValidBST(root.left, lowerBound, root.val ) && isValidBST(root.right, root.val, upperBound);
    }

    // Solution 2: using stack Inorder traversal
    public boolean isValid2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        while (root!= null || !stack.isEmpty()) {
            if (root!= null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                if (pre != null && pre.val >= root.val) return false;
                pre = root;
                root = root.right;
            }
        }
        return true;
    }

    // Solution 2.5, use in order traversal to save node in a list then compare each
    public boolean isValid(TreeNode root) {
        if (root == null) return true;
        List<Integer> list = new ArrayList<>();
        helper(list, root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) <= list.get(i)) return false;
        }
        return true;
    }

    private void helper(List<Integer> list, TreeNode root) {
        if (root.left != null) helper(list, root.left);
        list.add(root.val);
        if (root.right != null) helper(list, root.right);
        return;
    }


//    @Test
//    public void test() {
//        TreeNode root = TreeNode.createTree(Integer.MAX_VALUE, 3, Integer.MAX_VALUE);
//        System.out.println(isValidBST(root));
//    }
}
