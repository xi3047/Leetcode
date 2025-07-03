package round4.otherDataStructure;

import java.util.HashMap;
import java.util.Map;

public class L138_CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Step 1: Map original node -> new node
        Map<Node, Node> map = new HashMap<>();

        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // Step 2: Set next and random pointers
        curr = head;
        while (curr != null) {
            Node clone = map.get(curr);
            clone.next = map.get(curr.next);      // may be null
            clone.random = map.get(curr.random);  // may be null
            curr = curr.next;
        }

        return map.get(head);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
