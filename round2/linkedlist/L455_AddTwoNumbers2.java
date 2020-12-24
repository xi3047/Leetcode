package round2.linkedlist;

import org.junit.Test;
import round1.ListNode;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 12/22/2020 12:35 AM
 * @topic round2.linkedlist
 * @link https://leetcode.com/problems/add-two-numbers-ii/
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
public class L455_AddTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode dummy = null;
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int first = s1.isEmpty() ? 0 : s1.pop();
            int second = s2.isEmpty()? 0 : s2.pop();
            int sum = first + second + carry;
            ListNode newNode = new ListNode (sum % 10);
            newNode.next = dummy;
            dummy = newNode;
            carry = sum / 10;

        }
        return dummy;
    }


    @Test
    public void test() {
    }


}
