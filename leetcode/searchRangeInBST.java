package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-03-02
    @time:   11:27

    LintCode Q11: https://www.lintcode.com/problem/search-range-in-binary-search-tree/description
    Input:
        20
       /  \
      8   22
     / \
    4   12
    k1 = 10, k2 = 22
    Output:
    [12,20,22]
 */
public class searchRangeInBST {
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in increasing order.
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> res = new ArrayList<>();
        helper(root, k1, k2, res);
        return res;
    }

    private void helper(TreeNode root, int k1, int k2, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.val > k1) {
            helper(root.left, k1, k2, res);
        }
        if (root.val >= k1 && root.val <= k2) {
            res.add(root.val);
        }
        if (root.val < k2) {
            helper(root.right, k1, k2, res);
        }
    }

}
