package round4.otherDataStructure;

import round1.TreeNode;

import java.util.*;

public class L2096_StepByStepDirectionsFromBinaryTreeNode {

    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = findLCA(root, startValue, destValue);
        StringBuilder startPath = new StringBuilder();
        StringBuilder destPath = new StringBuilder();
        findPath(lca, startValue, startPath);
        findPath(lca, destValue, destPath);
        StringBuilder res = new StringBuilder();
        res.append("U".repeat(startPath.length()));
        res.append(destPath);

        return res.toString();
    }

    private void findPath(TreeNode node, int target, StringBuilder path) {
        if (node == null) return;
        if (node.val == target) return ;

        path.append('L');
        findPath(node.left, target, path);
        path.setLength(path.length() - 1);

        path.append('R');
        findPath(node.right, target, path);
        path.setLength(path.length() - 1);

    }


    private TreeNode findLCA(TreeNode root, int a, int b) {
        if (root == null || root.val == a || root.val == b) return root;

        TreeNode left = findLCA(root.left, a, b);
        TreeNode right = findLCA(root.right, a , b);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}
