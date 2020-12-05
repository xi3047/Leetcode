package round1;

/*
Time complexity: O(logn)
 */
public class L285_inorderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ret = null;
        TreeNode cur = root;
        boolean isContains = false;
        while (cur != null) {
            if (cur == p) {
               isContains = true;
            }
            if (cur.val <= p.val) {
                cur = cur.right;
            } else {
                ret = cur;
                cur = cur.left;
            }
        }
        if (!isContains) throw new RuntimeException("Target is not found is BST");
        return ret;
    }
}
