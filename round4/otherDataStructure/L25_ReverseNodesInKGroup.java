package round4.otherDataStructure;

import round1.ListNode;

public class L25_ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        int count = 0;
        ListNode check = head;
        while (check != null && count < k) {
            check = check.next;
            count++;
        }
        if (count < k) return head;

        ListNode prev = null, cur = head;
        count = 0;
        while (count < k) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            count++;
        }
        head.next = reverseKGroup(cur, k);
        return prev;
    }
}
