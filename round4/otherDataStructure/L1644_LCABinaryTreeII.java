package round4.otherDataStructure;

import round1.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class L1644_LCABinaryTreeII {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Set<Integer> set = new HashSet<>();
        TreeNode lca = findLCA(root, p, q, set);
        if (set.contains(p.val) && set.contains(q.val)) {
            return lca;
        }
        return null;
    }

    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q, Set<Integer> set) {
        if (root == null) return null;

        if (root.val == p.val || root.val == q.val) {
            set.add(root.val);
            return root;
        }

        TreeNode left = findLCA(root.left, p, q, set);
        TreeNode right = findLCA(root.right, p, q, set);

        if (left != null && right != null) return root;
        return left != null ? left : right;

    }
}
