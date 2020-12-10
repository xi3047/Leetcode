package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 11/24/2020 9:49 PM
 * @topic round2.linkedlist
 * @link
 * @description
 */
public class L206_ReverseLinkedlist {
    /**
     * Iterative
     */
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
    /**
     * Recursive
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
