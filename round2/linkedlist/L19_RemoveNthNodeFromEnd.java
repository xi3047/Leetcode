package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 12/24/2020 6:03 PM
 * @topic round2.linkedlist
 * @link https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * @description
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Follow up: Could you do this in one pass?
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 */
public class L19_RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode slow = dummy, fast = dummy;
        dummy.next = head;
        while (n -- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
