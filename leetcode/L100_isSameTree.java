package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-03-01
    @time:   20:03

    Follow up: L572. subtree of another tree
 */
public class L100_isSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
