package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 12/24/2020 11:25 PM
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
    /**
     * Solution:
     * https://leetcode.com/problems/reverse-linked-list-ii/discuss/30666/Simple-Java-solution-with-clear-explanation
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        ListNode start = pre.next;
        ListNode then = start.next;

        for (int i = 0; i < n - m; i ++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }
}
