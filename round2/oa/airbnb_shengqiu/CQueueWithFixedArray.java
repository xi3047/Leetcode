package round2.oa.airbnb_shengqiu;

/*
Assumption:
1. Implement a queue using array, array size has to be < 5
2. implement offer() and poll()

Approach:
Since the size of array is fixed, we can use a linked list to
store the array. Each ListNode has a array of fixed size.
For example
node1 -> node2 -> node3
node1 has a array of size 5
node2 has a array of size 5
when we need offer new value, we add to node3
if we want to poll value, we poll from node1

Time: O(1) for offer and poll
Space: O(n)
however, we use ListNode which is kind of overhead
 */
public class CQueueWithFixedArray {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        for (int i = 0; i < 20; i++) {
            System.out.println(queue.offer(i));
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(queue.poll());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.offer(i));
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.poll());
        }
    }
}

class MyQueue {
    private final static int FIXED_SIZE = 5;
    private int count;
    private ListNode headList;
    private ListNode tailList;
    private int head;
    private int tail;

    public MyQueue() {
        this.headList = new ListNode();
        this.tailList = headList;
    }

    public boolean offer(int val) {
        if (tail == FIXED_SIZE - 1) {
            tail = 0;
            tailList.next = new ListNode();
            tailList = tailList.next;
        }
        tailList.array[tail++] = val;
        count++;
        return true;
    }

    public Integer poll() {
        if (count == 0) return null;
        if (head == FIXED_SIZE - 1) {
            head = 0;
            headList = headList.next;
        }
        count--;
        return headList.array[head++];
    }

    class ListNode {
        int[] array;
        ListNode next;

        public ListNode() {
            this.array = new int[FIXED_SIZE];
            this.next = null;
        }
    }
}