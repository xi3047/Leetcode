package round2.linkedlist;

import leetcode.ListNode;

/**
 * @author Xi Zhang
 * @date 11/24/2020 11:08 PM
 * @topic round2.linkedlist
 * @link https://leetcode.com/problems/palindrome-linked-list/
 * @description
 */
public class L234_PalidromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        slow = reverse(slow);
        while (slow != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
