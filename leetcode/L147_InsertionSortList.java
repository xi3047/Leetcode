package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-06-03
    @time:   17:13

    Sort a linked list using insertion list
    Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 */
public class L147_InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        ListNode cur = head;

        while (cur.next != null) {
            if (cur.next.val < cur.val) {
                ListNode curNext = new ListNode(cur.next.val);
                cur.next = cur.next.next;
                ListNode newCur = dummy.next;
                ListNode prev = dummy;
                while (curNext.val > newCur.val) {
                    prev = newCur;
                    newCur = newCur.next;
                }
                prev.next = curNext;
                curNext.next = newCur;
                continue;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
