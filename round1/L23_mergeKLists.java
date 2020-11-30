package round1;
/*
    @author: Xi Zhang
    @date:   2019-05-03
    @time:   12:11

    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6


 */
import java.util.PriorityQueue;
import java.util.Queue;

public class L23_mergeKLists {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    /**
     * Use a minHeap to get the minimum node first and attach it to a new listNode dummy
    */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        Queue<ListNode> queue = new PriorityQueue<>((a,b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // add the heads to the heap if it is not null
        for (ListNode head: lists) {
            if (head != null) queue.offer(head);

        }

        while (!queue.isEmpty()) {
            // 取出最小的然后放到cur后面，更新cur
            cur.next = queue.poll();
            cur = cur.next;

            // 放入刚才的后一个进heap，如果不是null的话
            if (cur.next != null) {
                queue.offer(cur.next);
            }
        }
        return dummy.next;
    }

}
