package leetcode;

import java.util.HashMap;

/*
    @author: Xi Zhang
    @date:   2/6/19
    @time:   7:27 PM
 */
public class L146_LRU_Cache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    class LRUCache {
        HashMap<Integer, Node> map;
        int capacity;
        Node head, tail;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public void deleteNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void addAtHead(Node node) {
            node.next = head.next;
            node.next.prev = node;
            node.prev = head;
            head.next = node;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                int result = node.value;
                deleteNode(node);
                addAtHead(node);
                return result;
            }
            return -1;
        }

        public void put(int key, int val) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                deleteNode(node);
                node.value = val;
                addAtHead(node);
            } else {
                Node newNode = new Node(key, val);
                if (map.size() < capacity) {
                    addAtHead(newNode);
                    map.put(key, newNode);
                } else {
                    map.remove(tail.prev.key);
                    deleteNode(tail.prev);
                    addAtHead(newNode);
                    map.put(key, newNode);
                }
            }
        }
    }
}
