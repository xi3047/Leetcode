package round4.otherDataStructure;

import java.util.HashMap;
import java.util.Map;

public class L146_LRUCache {
    class LRUCache {
        int capacity;
        Map<Integer, Node> map; // store
        Node head;
        Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // if it contains key, update its usage, return value
        // return -1
        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            int value = node.val;
            deleteNode(node);
            addToHead(node);
            return value;
        }

        // if not exist: create new node and add to head, evict tail node when capacity is reached
        // if exist, update its value and move that node to the head
        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                Node newNode = new Node(key, value);
                addToHead(newNode);
                map.put(key, newNode);
                if (map.size() > capacity) {
                    map.remove(tail.prev.key);
                    deleteNode(tail.prev);
                }
            } else {
                Node node = map.get(key);
                node.val = value;
                deleteNode(node);
                addToHead(node);
            }
        }

        // head -> node1   ==>  head  -> newNode -> node1
        // head <- node1   ==>  head  <- newNode <- node1
        private void addToHead(Node newNode) {
            Node node1 = head.next;
            head.next = newNode;
            newNode.next = node1;
            newNode.prev = head;
            node1.prev = newNode;
        }

        //  prev ->  node -> next  =>  prev -> next
        //  prev <-  node <- next  =>  prev <- next
         private void deleteNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev= prev;
        }
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node (int k, int v) {
            key = k;
            val = v;
            prev = null;
            next = null;
        }
    }
}
