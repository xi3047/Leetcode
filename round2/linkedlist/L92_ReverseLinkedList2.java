package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 11/30/2020 9:15 PM
 * @topic round2.linkedlist
 * @link https://leetcode.com/problems/reverse-linked-list-ii/
 * @description
 * Reverse a linked list from position m to n. Do it in one-pass.
 *
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public class L92_ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode cur = head;
        return reverse(cur.next);

    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
