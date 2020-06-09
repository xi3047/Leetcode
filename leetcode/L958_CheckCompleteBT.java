package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
    @author: Xi Zhang
    @date:   2019-02-18
    @time:   21:37

 */
public class L958_CheckCompleteBT {
    // Solution 1: level order traversal, use a hasNull flag
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasNull = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                if (hasNull == true) return false;
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                hasNull = true;
            }
        }
        return true;
    }

    // Solution 2:
    public boolean isComplete(TreeNode root) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasNull = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (hasNull == true) return false;
                queue.offer(cur.left);
            } else {
                hasNull = true;
            }
            if (cur.right != null) {
                if (hasNull == true) return false;
                queue.offer(cur.right);
            } else {
                hasNull = true;
            }
        }
        return true;
    }

    // Solution 3:


}
