package round1_misc.amazon;

/**
    @author: Xi Zhang
    @date:   2019-02-22
    @time:   17:24
    Amazon OA question 3
 **/
import round1.TreeNode;

        import org.junit.Test;

public class BstDistance {

    public int bstDistance(int[] values, int n, int node1, int node2) {

        boolean n1Exist = false, n2Exist = false;
        TreeNode root = new TreeNode(values[0]);
        if (root.val == node1) n1Exist = true;
        if (root.val == node2) n2Exist = true;
        for (int i = 1; i < values.length; i++) {
            if (values[i] == node1) n1Exist = true;
            if (values[i] == node2) n2Exist = true;
            insertIntoBST(root, values[i]);
        }

        if (!n1Exist || !n2Exist) return -1;
        return distanceBtwTwoNodes(root, node1, node2);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    private int distanceFromRoot(TreeNode root, int x) {
        if (x == root.val) return 0;
        if (x < root.val) return 1 + distanceFromRoot(root.left, x);
        return 1 + distanceFromRoot(root.right, x);
    }

    private int distanceBtwTwoNodes(TreeNode root, int node1, int node2) {
        TreeNode lca = lowestCommonAncestor(root, new TreeNode(node1), new TreeNode(node2));
        return distanceFromRoot(lca, node1) + distanceFromRoot(lca, node2);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val > p.val && root.val > q.val) lowestCommonAncestor(root.left, p ,q);
            if (root.val < p.val && root.val < q.val) lowestCommonAncestor(root.right, p, q);
            return root;
    }

    @Test
    public void test() {
        int[] values = {5, 3, 9, 1, 4, 6, 10, 7};

        TreeNode root = new TreeNode(values[0]);
        for (int i = 1; i < values.length; i++) {
            insertIntoBST(root, values[i]);
        }
        TreeNode.visualize(root);
        int res = bstDistance(values, 8, 1, 7);
        System.out.println(res);
    }

}
