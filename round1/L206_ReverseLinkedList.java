package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2/12/19
    @time:   12:00 AM
 */
public class L206_ReverseLinkedList {
    // Recursion
    public ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    // Iterative Solution leetcode
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }


    public ListNode hasCycle(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while(runner.next!=null && runner.next.next!=null) {
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner) return walker ;
        }
        return null;
    }

    @Test
    public void test() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = a;
        ListNode cyclePoint = hasCycle(c);
        while (c.next != cyclePoint) {
            System.out.print(c.val + " ");
            c = c.next;
        }

    }
}
