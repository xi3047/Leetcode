package round2.oa.airbnb;

/**
 * @author Xi Zhang
 * @date 1/14/21 9:04 PM
 * @topic round2.oa.airbnb
 * @link
 * @description
 * Assumption:
 * 1. Implement a queue using array, array size has to be < 5
 * 2. implement offer() and poll()
 *
 * Approach:
 * Since the size of array is fixed, we can use a linked list to
 * store the array. Each ListNode has a array of fixed size.
 * For example
 * node1 -> node2 -> node3
 * node1 has a array of size 5
 * node2 has a array of size 5
 * when we need offer new value, we add to node3
 * if we want to poll value, we poll from node1
 *
 * Time: O(1) for offer and poll
 * Space: O(n)
 * however, we use ListNode which is kind of overhead
 */


public class QueueWithFixedArrayLinkedList {
    class MyQueue{
        private static final int FIXED_SIZE = 5;
        private int count; // total number of elements
        private ListNode headArray; // the first array
        private ListNode tailArray; // the last array
        private int head; // position in the first array, used for removing first element
        private int tail; // position in the last array, used for removing last element
        public MyQueue() {
            // create one array
            this.headArray = new ListNode();
            this.tailArray = headArray;
        }

        public void offer(int val) {

            // if we are at the last position in an array
            // we have to create a new array, link it to the end of our linkedlist
            // and reset tail to start from 0 in the new array
            if (tail == FIXED_SIZE - 1) {
                ListNode newArray = new ListNode();
                tailArray.next = newArray;
                tailArray = newArray;
                tail = 0;
            }
            // now we can add the element to end of the the last array
            tailArray.array[tail++] = val;
            count++;
        }

        public Integer poll() {
            // there are no size, we return null
            if (count == 0) return null;
            // if we are at the end of our first array, we don't need it anymore, we can remove it
            // also reset head position
            if (head == FIXED_SIZE) {
                headArray = headArray.next;
                head = 0;
            }

            // now we can remove the element from the beginning of the first array
            count--;
            return headArray.array[head++];
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







}
