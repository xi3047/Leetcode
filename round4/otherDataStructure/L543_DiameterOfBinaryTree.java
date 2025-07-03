package round4.otherDataStructure;

import round1.TreeNode;

public class L543_DiameterOfBinaryTree {
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return diameter;
    }

    private int maxDepth(TreeNode node) {
        if (node == null) return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }

}
