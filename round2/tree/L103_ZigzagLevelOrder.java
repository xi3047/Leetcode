package round2.tree;

import round1.TreeNode;

import java.util.ArrayList;
import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/18/2020 12:21 PM
 * @topic round2.tree
 * @link
 */
public class L103_ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int odd = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                if (odd == -1) {
                    level.add(0, cur.val);
                } else {
                    level.add(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            odd *= -1;
            res.add(level);
        }
        return res;
    }
}
