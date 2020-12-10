package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 12/5/2020 2:29 PM
 * @topic round2.linkedlist
 * @link
 * @description
 */
public class L141_LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
