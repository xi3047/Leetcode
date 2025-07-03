package round4.otherDataStructure;

import round1.TreeNode;

import java.util.*;

public class L987_VerticalOrderTraversalOfBinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            TreeNode curNode = node.treeNode;
            int row = node.row, col = node.col;
            map.computeIfAbsent(col, x -> new TreeMap<>())
                    .computeIfAbsent(row, x -> new PriorityQueue<>())
                    .offer(curNode.val);

            if (curNode.left != null) {
                queue.offer(new Node(curNode.left, row + 1, col - 1));
            }
            if (curNode.right != null) {
                queue.offer(new Node(curNode.right, row + 1, col + 1));
            }
        }

        for (TreeMap<Integer, PriorityQueue<Integer>> colMap : map.values()) {
            List<Integer> colList = new ArrayList<>();
            for (PriorityQueue<Integer> pq : colMap.values()) {
                while (!pq.isEmpty()) {
                    colList.add(pq.poll());
                }
            }
            res.add(colList);
        }
        return res;
    }

    class Node {
        TreeNode treeNode;
        int row, col;

        Node(TreeNode node, int r, int c) {
            treeNode = node;
            row = r;
            col = c;
        }
    }

}
