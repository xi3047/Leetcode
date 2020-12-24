package round2.tree;

import round1.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/19/2020 1:04 PM
 * @topic round2.tree
 * @link
 * @description
 */
public class L99_RecoverBinarySearchTree {
    /*
    Solution 1, inorder then recover
     */
    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    private int[] findTwoSwapped(List<Integer> nums) {
        int x = -1, y = -1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < nums.get(i-1)) {
                y = nums.get(i);
                // only record the first occurence of x
                if (x == -1) x = nums.get(i-1);
                else break;
            }
        }
        return new int[]{x ,y};
    }

    private void recover(TreeNode root, int count, int x, int y) {
        if (root == null || count == 0) return;
        if (root.val == x || root.val == y) {
            root.val = root.val == x ? y : x;
            count--;
        }
        recover(root.left, count, x, y);
        recover(root.right, count, x, y);
    }

    /*
    Solution 2 Recursive
     */
    TreeNode x = null, y = null, pred = null;

    public void recoverTree2(TreeNode root) {
        findTwoSwapped(root);
        swap(x, y);
    }

    public void findTwoSwapped(TreeNode root) {
        if (root == null) return;
        findTwoSwapped(root.left);
        if (pred != null && root.val < pred.val) {
            y = root;
            if (x == null) x = pred;
            else return;
        }
        pred = root;
        findTwoSwapped(root.right);
    }

    public void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }




}
