package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-03-01
    @time:   21:37
    Time complexity: O(max(m,n))
    Space complexity: O(max(m,n))
 */
public class L572_isSubtree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSameTree(s, t)) return true;
        return isSameTree(s.left, t) || isSameTree(s.right, t);

    }
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
