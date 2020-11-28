package round2.tree;

import leetcode.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/18/2020 9:45 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * @description Given an array where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth
 * of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class L108_ConvertSortedArrayToBST {
    /**
     * Use binary search to divide the array into left and right parts,
     * the mid point will serve as the root of the divided subtree
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return divide(nums, 0, nums.length -1);
    }

    private TreeNode divide(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode newMid = new TreeNode(nums[mid]);
        newMid.left = divide(nums, left, mid - 1);
        newMid.right = divide(nums, mid + 1, right);
        return newMid;
    }
}
