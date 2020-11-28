package round2.tree;

import leetcode.TreeNode;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/19/2020 9:33 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/binary-tree-vertical-order-traversal/submissions/
 * @description
 */
public class L314_BTVerticalOrderTraversal {
    /**
     * Solution, using two hashmaps, one to store Node to column, one to store column to list of values
     * Trick, use a min to keep track of leftmost column to avoid sorting the keys at the end
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        // map of treenode to corresponding column
        Map<TreeNode, Integer> column = new HashMap<>();
        // map of weight to list of corresponding node values
        Map<Integer, List<Integer>> map = new HashMap<>();
        // min value to keep track leftmost column
        int min = 0;
        queue.offer(root);
        column.put(root, 0);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curColumn = column.get(cur);
            map.putIfAbsent(curColumn, new ArrayList<>());
            map.get(curColumn).add(cur.val);
            if (cur.left != null) {
                int i = curColumn - 1;
                column.put(cur.left, i);
                queue.offer(cur.left);
                min = Math.min(i, min);
            }
            if (cur.right != null) {
                int j = curColumn + 1;
                column.put(cur.right, j);
                queue.offer(cur.right);
            }

        }
        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }
        return res;
    }
}




