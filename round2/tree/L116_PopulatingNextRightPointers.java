package round2.tree;
/**
 * @author Xi Zhang
 * @date 11/19/2020 3:14 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * @description
 */
public class L116_PopulatingNextRightPointers {
    public Node connect(Node root) {
        if (root == null) return null;
        Node head = root;
        while (head.left != null) {
            Node cur = head;
            while (cur != null) {
                cur.left.next = cur.right;
                cur.right.next = cur.next == null? null : cur.next.left;
                cur = cur.next;
            }
            head = head.left;
        }
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};