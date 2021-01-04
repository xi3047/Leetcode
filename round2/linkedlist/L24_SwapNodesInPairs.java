package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 12/24/2020 6:09 PM
 * @topic round2.linkedlist
 * @link https://leetcode.com/problems/swap-nodes-in-pairs/
 * @description
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes. Only nodes itself may be changed.

 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 */
public class L24_SwapNodesInPairs {
    /**
     * Recursive
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode first = head;
        ListNode second = head.next;

        first.next = swapPairs(second.next);
        second.next = first;

        return second;
    }
    /**
     * Iterative
     */
    public ListNode swapPairs2(ListNode head) {

        // Dummy node acts as the prevNode for the head node
        // of the list and hence stores pointer to the head node.
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevNode = dummy;

        while ((head != null) && (head.next != null)) {

            // Nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // Swapping
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // Reinitializing the head and prevNode for next swap
            prevNode = firstNode;
            head = firstNode.next; // jump
        }

        // Return the new head node.
        return dummy.next;
    }
}
