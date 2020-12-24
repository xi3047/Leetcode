package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 11/27/2020 7:06 PM
 * @topic round2.linkedlist
 * @link https://leetcode.com/problems/add-two-numbers/
 * @description
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class L2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            int sum = carry + (l1 == null? 0 : l1.val) + (l2 == null ? 0 :l2.val);
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }



    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
