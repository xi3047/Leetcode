package leetcode;

import org.junit.Test;

public class L236_LCABT {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null? left: right;
    }

    @Test
    public void test() {
        Integer[] arr = {1, 2, 3,4,5,6};
        TreeNode root = TreeNode.createTree(arr);
        TreeNode.visualize(root);
        TreeNode res = lowestCommonAncestor(root, new TreeNode(6), new TreeNode(4));
        System.out.println(res.val);
    }

}
