package leetcode;

import java.util.*;

public class L138_CopyRandomList {

     // Definition for singly-linked list with a random pointer.
      class RandomListNode {
          int label;
          RandomListNode next, random;
          RandomListNode(int x) { this.label = x; }
      }

    // Solution 1 three pass, copy every node and attach them to every node, space O(1)
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return head;
        RandomListNode cur = head;
        RandomListNode savedNext;
        // make a copy of every node and attach it right after it
        while (cur != null) {
            savedNext = cur.next;
            cur.next = new RandomListNode(cur.label);
            cur.next.next = savedNext;
            cur = savedNext;
        }

        // assign random pointers for every node
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // restore the original list and extract copy list
        cur = head;
        RandomListNode copyHead = head.next;
        RandomListNode copy = copyHead;

        while (cur.next.next != null) {
            cur.next = cur.next.next;
            cur = cur.next;

            copy.next = copy.next.next;
            copy = copy.next;
        }
        cur.next = null;
        return copyHead;
    }

    // Solution 2 Using HashMap, two pass, space O(n)
    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        // first pass, copy all the nodes and save their relationship in the hashmap
        RandomListNode cur = head;
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }

        // second pass, assign next and random pointers
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
