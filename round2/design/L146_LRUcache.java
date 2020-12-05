package round2.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 11/28/2020 8:57 AM
 * @topic round2.design
 * @link https://leetcode.com/problems/lru-cache/
 * @description Design Least Recently Used Cache
 */
public class L146_LRUcache {
    /*
    Solution: Use a double linkedlist for ordering of the key-value pairs,
    so we need to create a Node with key,val and previous nad next pointers,
    to perform O(1) operation on get and put, we use a hashmap, with node's key as the map's key,
    node as map's value

    Initialization: we have a empty hashmap and one head Node that implies most recently used and
    one tail node that implies the least recently used.

    to perform get(), we need to check if the key exists in the map first, if it does not exist we return -1m
    if it exists, we get the value and delete the node in the double linkedlist and add this node to the head of the
    linkedlist, no need to update the map

    to perform put(), there are two cases:
    case 1: the key exists in the map, simply update the node's value and move the node to the head position
    case 2: the map does not exist in the map, there are two sub-cases:
       sub-case 1: capacity not reached, we add the key-node pair to the map and add the node to the head
       sub-case 2: capacity is reached, we have to delete the tail node and tail key-node pair from map,
       then put the new node to the head of the double linkedlist and also put new key-node pair in the map
     */
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node (int k, int v) {
            this.key = k;
            this.val = v;
            this.prev = null;
            this.next = null;
        }
    }

    Map<Integer, Node> map;
    int capacity;
    Node head, tail;

    public L146_LRUcache(int capacity) {
        map = new HashMap<>();
        tail = new Node(0,0);
        head = new Node(0, 0);
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }

    public void addAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int val = node.val;
            deleteNode(node);
            addAtHead(node);
            return val;
        }
        return -1;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            // update and put it at head
            Node node = map.get(key);
            node.val = val;
            deleteNode(node);
            addAtHead(node);
        } else {
            Node newNode = new Node(key, val);
            // case 1 if at max capacity, remove tail, add node
            if (map.size() >= capacity) {
                Node lastNode = tail.prev;
                deleteNode(lastNode);
                map.remove(lastNode.key);
                addAtHead(newNode);
                map.put(key, newNode);
            } else {
                // if not full, simply add to head
                addAtHead(newNode);
                map.put(key, newNode);
            }
        }
    }
}
