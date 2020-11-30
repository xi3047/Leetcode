package round1;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode extends Node<Integer> {
    public int val;

    public TreeNode left, right;

    public TreeNode(int val) {
        super(val);
        this.val = val;
        left = right = null;
    }

    @Override
    public Integer getData() {
        return val;
    }

    @Override
    Node<Integer> left() {
        return left;
    }

    @Override
    Node<Integer> right() {
        return right;
    }

    public static TreeNode createTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            if (count >= arr.length-1) {
                break;
            }
            TreeNode curr = queue.poll();

            count++;
            if (arr[count] != null) {
                curr.left = new TreeNode(arr[count]);
                queue.offer(curr.left);
            }
            count++;
            if (count >= arr.length) break;
            if ((arr[count])!=null) {
                curr.right = new TreeNode(arr[count]);
                queue.offer(curr.right);
            }
        }
        return root;
    }

    public static TreeNode createTree(String data) {
        if (data == null) return null;
        String[] nodes = data.split(",");
        Integer [] arr = new Integer[nodes.length];
        for(int i=0;i<arr.length;i++) {
            arr[i] = nodes[i].equals("null")? null : Integer.valueOf(nodes[i]);
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            if (count >= arr.length-1) {
                break;
            }
            TreeNode curr = queue.poll();

            count++;
            if (arr[count] != null) {
                curr.left = new TreeNode(arr[count]);
                queue.offer(curr.left);
            }
            count++;
            if ((arr[count])!=null) {
                curr.right = new TreeNode(arr[count]);
                queue.offer(curr.right);
            }
        }
        return root;

    }

    public static void visualize(TreeNode node) {
        BinaryTreePrinter.printNode(node);
    }

    public static void inOrderTraversal(TreeNode node) {
        inOrderTraversalHelper(node);
        System.out.println();
    }

    private static void inOrderTraversalHelper(TreeNode node) {
        if (node == null) return;

        inOrderTraversalHelper(node.left);
        System.out.print(node.val + " ");
        inOrderTraversalHelper(node.right);
    }



}