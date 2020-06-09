package leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class L222_countCompleteBinaryTree {
    public int countNodes1(TreeNode root) {
        int count = 0;
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        count++;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                    count++;
                } else {
                    return count;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    count++;
                } else {
                    return count;
                }
            }
        }
        return count;
    }

    public int countNodes(TreeNode root) {
        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);
        if (leftDepth == rightDepth) return (1 << leftDepth) - 1;
        else return 1 + countNodes(root.left) + countNodes(root.right);
    }
    private int leftDepth(TreeNode root) {
        int dep = 0;
        while (root != null) {
            root = root.left;
            dep++;
        }
        return dep;
    }
    private int rightDepth(TreeNode root) {
        int dep = 0;
        while (root != null) {
            root = root.right;
            dep++;
        }
        return dep;
    }




    @Test
    public void test() {
//        Integer[] values = {1, 2, 3, 4, 5, 6};
//        TreeNode root = TreeNode.createTree(values);
//
//        System.out.println(countNodes(root));
        System.out.println(1 << 3);

    }
}
