package round2.linkedlist;

import org.junit.Test;
import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 12/24/2020 7:08 PM
 * @topic round2.linkedlist
 * @link https://leetcode.com/problems/reverse-nodes-in-k-group/
 * @description
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * Follow up:
 *
 * Could you solve the problem in O(1) extra memory space?
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *xample 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * Example 3:
 *
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 */
public class L25_ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        //1. test weather we have more then k node left, if less then k node left we just return head
        ListNode cur = head;
        int count = 0;
        while (count < k) {
            if(cur == null)return head;
            cur = cur.next;
            count++;
        }
        // 2.reverse k node at current level
        ListNode pre = reverseKGroup(cur, k); //pre node point to the the answer of sub-problem
        while (count > 0) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            count = count - 1;
        }
        return pre;
    }
    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = reverseKGroup(head, 2);
        while (res != null) {
            System.out.print(res.val +" ");
            res = res.next;
        }
    }
}
