package round2.linkedlist;

/**
 * @author Xi Zhang
 * @date 1/28/21 9:36 下午
 * @topic round2.linkedlist
 * @link https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
 * @description
 * Nordstrom Phone Interview Question 1/28/2021
 *
 */
public class L708_InsertIntoSortedCircularLinkedList {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node max = head;
        // find the maximum head
        while (max.next != head && max.val <= max.next.val) {
            max = max.next;
        }
        // the min head is the node after the max
        Node min = max.next, cur = min;

        // case 1: when insert val is greater than max or smaller than min, we should append it at the end
        if (insertVal >= max.val || insertVal <= min.val) {
            Node node = new Node(insertVal, min);
            max.next = node;
        } else {
            // case 2: when insert val is in between the nodes, we should insert into its correct position
            while (insertVal >= cur.next.val) {
                cur = cur.next;
            }
            Node node = new Node(insertVal, cur.next);
            cur.next = node;
        }
        return head;
    }
}
