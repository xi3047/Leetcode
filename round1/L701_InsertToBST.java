package round1;

/*
    @author: Xi Zhang
    @date:   2019-02-25
    @time:   15:33
 */
public class L701_InsertToBST {

    // Solution 1, recursion
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    // Solution 2 Iterative
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode cur = root;
        while (true) {
            if (cur.val <= val) {
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
