package leetcode;

public class L426_treeToDoublyList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    Node head = null;
    Node prev = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        inOrder(root);
        prev.right = head;
        head.left = prev;
        return head;
    }
    private void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        // do something
        if (prev != null) prev.right = root;
        else head = root;
        root.left = prev;
        prev = root;
        inOrder(root.right);
    }
}
