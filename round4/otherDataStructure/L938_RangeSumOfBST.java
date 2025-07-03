package round4.otherDataStructure;

import com.sun.source.tree.Tree;
import round1.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class L938_RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            if (cur.val >= low && cur.val <= high) {
                sum += cur.val;
            }
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
        return sum;
    }
}
