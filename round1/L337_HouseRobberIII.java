package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-13
    @time:   11:44
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class L337_HouseRobberIII {
    // Solution 1: Brute force recursion
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(root.val + val, rob(root.left)+ rob(root.right));
    }

    // Solution 2: use a int[] to save the values of not robbing current node and maximum of robbing cur
    public int rob2(TreeNode root) {
        return maxMoney(root)[1];
    }

    private int[] maxMoney(TreeNode root) {
        if (root == null) return new int[2];
        int[] left = maxMoney(root.left);
        int[] right = maxMoney(root.right);
        int[] res = new int[2];

        res[0] = left[1] + right[1]; // we don't rob root
        int robCur = root.val + left[0] + right[0]; // we rob root
        res[1] = Math.max(robCur, res[0]); // res[1] will be max of prev two

        return res;
    }

    @Test
    public void test() {
        Integer[] values = {3, 4, 5, 1, 2, null, 1};
        TreeNode root = TreeNode.createTree(values);
        TreeNode.visualize(root);
        System.out.println(rob2(root));
    }


}
