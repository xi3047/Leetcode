package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-03-02
    @time:   11:45
    Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 2.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
 */
public class L270_closestValue {
    public int closestValue(TreeNode root, double target) {
        if (root == null) return root.val;
        TreeNode closest = root;
        TreeNode cur = root;
        while (cur != null) {
            //如果比较时两个数值其中有一个是double，不能用双等，因为二进制存小数时会损失精度
            if (Math.abs(cur.val - target) <= (10^-10)) return cur.val;
            if (Math.abs(cur.val - target) < Math.abs(closest.val - target)) closest = cur;
            if (cur.val < target) cur = cur.right;
            else {cur = cur.left;}
        }
        return closest.val;
    }

    public int closestValueR(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - res)){
                res = root.val;
            }
            root = root.val > target? root.left : root.right;
        }
        return res;
    }

    // Find the TreeNode that is the largest yet smaller than the target
    public TreeNode largestSmaller(TreeNode root, double target) {
        if (root == null) return null;
        TreeNode res = root;
        while (root != null) {
            if (root.val >= target) {
                root = root.left;
            } else { // root.val < target
                if (target - root.val < target - res.val){
                    res = root;
                }
                root = root.right;
            }
        }
        return res.val > target ? null : res;
    }

    // Find the treenode that is the smallest but larger than target
    public TreeNode smallestLarger(TreeNode root, double target) {
        if (root == null) return null;
        TreeNode res = root;
        while (root != null) {
            if (root.val > target) {
                if (root.val - target < res.val - target){
                    res = root;
                }
                root = root.left;
            } else { // root.val < target
                root = root.right;
            }
        }
        return res.val < target ? null : res;
    }
}

