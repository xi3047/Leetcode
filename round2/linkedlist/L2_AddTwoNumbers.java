package round2.linkedlist;

import round1.ListNode;

/**
 * @author Xi Zhang
 * @date 11/27/2020 7:06 PM
 * @topic round2.linkedlist
 * @link
 * @description
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
