package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 12/5/2020 5:10 PM
 * @topic round2.linkedlist
 * @link
 * @description
 */
public class L142_LinkedListCycle2 {
    /*
    Floyd's algorithm
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode start = head;
                while (start != slow) {
                    slow = slow.next;
                    start = start.next;
                }
                return start;
            }
        }
        return null;
    }
}
