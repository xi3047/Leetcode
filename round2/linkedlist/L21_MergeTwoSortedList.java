package round2.linkedlist;

import leetcode.ListNode;
import org.junit.Test;

public class L21_MergeTwoSortedList {
    // recursion
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // Iterative using a dummy
    public ListNode mergeTwoListsIterative(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

   @Test
    public void test() {
        ListNode head = new ListNode(2);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(4);

        ListNode head2 = new ListNode(1);
        ListNode n10 = new ListNode(3);
        ListNode n11 = new ListNode(4);
//        head.next = n1;
//        n1.next = n2;
//        head2.next = n10;
//        n10.next = n11;

        ListNode result = mergeTwoLists(head, head2);

        while (result != null) {
            System.out.print(result.val);
            result = result.next;

        }
    }
}
