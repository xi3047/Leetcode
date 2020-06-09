package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
    @author: Xi Zhang
    @date:   2019-03-01
    @time:   20:06
 */
public class L101_isSymmetricTree {
    // Recursion
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    // Iterative solution using bfs
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }


//    @Test
//    public void test() {
//        TreeNode root = TreeNode.createTree(1, 2, 2, 3, 4, 4, 3, 5, 6, 7, 8, 8, 7, 6, 5);
//        Assert.assertEquals(true, isSymmetric2(root));
//
//    }

}
