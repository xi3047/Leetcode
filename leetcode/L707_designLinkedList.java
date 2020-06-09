package leetcode;

class MyLinkedList {


    class DoublyListNode {
        int val;
        DoublyListNode prev, next;
        DoublyListNode (int val) {this.val = val;}
        DoublyListNode (int val, DoublyListNode prev, DoublyListNode next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private DoublyListNode head;
    //private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private DoublyListNode getNode(int index) {
        DoublyListNode cur = head;
        for (int i = 0; i < index && cur != null; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private DoublyListNode getTail(int index) {
        DoublyListNode cur = head;
        while (cur != null && cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        return getNode(index).val;
    }

    public void addAtHead(int val) {
        DoublyListNode cur = new DoublyListNode(val);
        cur.next = head;
        if (head != null) {
            head.prev= cur;
        }
        head = cur;
        size++;
    }
//
//    public void addAtTail(int val) {
//        Node n = new Node(val);
//        if (this.size++ == 0)
//            this.head = this.tail = n;
//        else
//            this.tail = this.tail.next = n;
//    }
//
//    public void addAtIndex(int index, int val) {
//        if (index < 0 || index > this.size) return;
//        if (index == 0)  { this.addAtHead(val); return; }
//        if (index == size) { this.addAtTail(val); return; }
//        Node prev = this.getNode(index - 1);
//        prev.next = new Node(val, prev.next);
//        ++this.size;
//    }
//
//    public void deleteAtIndex(int index) {
//        if (index < 0 || index >= this.size) return;
//        Node prev = this.getNode(index - 1);
//        prev.next = prev.next.next;
//        if (index == 0) this.head = prev.next;
//        if (index == this.size - 1) this.tail = prev;
//        --this.size;
//    }
}
