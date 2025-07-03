package round4.otherDataStructure;

import round1.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class L270_ClosestBinarySearchTreeValue {
    // Build in order array
    public int closestValue(TreeNode root, double target) {
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        int closest = root.val;
        for (int num : nums) {
            if (Math.abs(num - target) == Math.abs(closest - target)) {
                closest = Math.min(closest, num);
            }
            if (Math.abs(num - target) < Math.abs(closest - target)) {
                closest = num;
            }
        }
        return closest;
    }

    private void inOrder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }

    // use BST
    public int close(TreeNode root, double target) {
        int closest = root.val;
        while (root != null) {
            int cur = root.val;
            if (Math.abs(cur - target) < Math.abs(closest - target)
            || Math.abs(cur - target) == Math.abs(closest - target) && cur < closest) {
                closest = cur;
            }
            root = target < cur ? root.left : root.right;
        }
        return closest;
    }
}
