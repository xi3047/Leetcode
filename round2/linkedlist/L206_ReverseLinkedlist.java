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
        if (head == null) return head;
        ListNode cur = head;
        ListNode prev = null;
        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        cur.next = prev;
        return cur;
    }
    /**
     * Recursive
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
