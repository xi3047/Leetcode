package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-03-02
    @time:   20:06

103. Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */
import java.util.*;

public class    L103_zigzagLevelOrder {
    // Solution 1: using BFS / Level order traversal with a level counter
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> sofar = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode pollNode = queue.poll();
                sofar.add(pollNode.val);
                if (pollNode.left != null) queue.offer(pollNode.left);
                if (pollNode.right != null) queue.offer(pollNode.right);
            }
            if (level % 2 == 0) Collections.reverse(sofar);
            level++;
            result.add(sofar);
        }
        return result;
    }

    // Solution 2: using deque with a boolean flag
    public List<List<Integer>> zigzagLevelOrderDeque(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offerLast(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> sofar = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (flag == false) { //正常level
                    TreeNode node = queue.pollFirst(); // poll
                    sofar.add(node.val);
                    if (node.left != null) queue.offerLast(node.left); // offer
                    if (node.right != null) queue.offerLast(node.right);
                }
                else {
                    TreeNode node = queue.pollLast();
                    sofar.add(node.val);
                    if (node.right != null) queue.offerFirst(node.right);
                    if (node.left != null) queue.offerFirst(node.left);
                }
            }
            result.add(sofar);
            flag = !flag;
        }
        return result;
    }
}






