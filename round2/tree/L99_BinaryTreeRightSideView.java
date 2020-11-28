package round2.tree;
import leetcode.TreeNode;

import java.util.*;
/**
 * @author Xi Zhang
 * @date 11/18/2020 12:26 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/binary-tree-level-order-traversal/
 * @description Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class L99_BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.add(cur);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            while (size-- > 0) {
                cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(level.get(level.size() -1));
        }
        return res;
    }

    // Solution 2: DFS, preOrder to the right child, add the right node first
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res, 0);
        return res;
    }

    private void traverse(TreeNode root, List<Integer> result, int depth) {
        if (root == null) return;
        if(depth == result.size()){
            result.add(root.val);
        }
        traverse(root.right, result, depth + 1);
        traverse(root.left, result, depth + 1);
    }
}
