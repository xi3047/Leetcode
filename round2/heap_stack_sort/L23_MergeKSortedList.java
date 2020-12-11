package round2.heap_stack_sort;

import round1.ListNode;

import java.util.PriorityQueue;

/**
 * @author Xi Zhang
 * @date 12/11/2020 1:31 PM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/merge-k-sorted-lists/
 * @description
 */
public class L23_MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length ==0) return null;

        PriorityQueue<ListNode> minHeap= new PriorityQueue<ListNode>((a,b) -> a.val - b.val);

        ListNode dummy = new ListNode(0);
        ListNode cur=dummy;

        for (ListNode node:lists)
            if (node!=null)
                minHeap.add(node);

        while (!minHeap.isEmpty()){
            cur.next=minHeap.poll();
            cur=cur.next;

            if (cur.next!=null)
                minHeap.add(cur.next);
        }
        return dummy.next;
    }

}
