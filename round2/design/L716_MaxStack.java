package round2.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @author Xi Zhang
 * @date 11/28/2020 10:59 PM
 * @topic round2.design
 * @link
 * @description
 */
public class L716_MaxStack {


    /** initialize your data structure here. */
    Deque<Integer> deque;
    PriorityQueue<Integer> maxHeap;

    public L716_MaxStack() {
        deque = new ArrayDeque<>();
        maxHeap = new PriorityQueue<>((a, b) -> (b - a));

    }

    public void push(int x) {
        deque.addLast(x);
        maxHeap.offer(x);
    }

    public int pop() {
        int deleteElement = deque.removeLast();
        maxHeap.remove(deleteElement);
        return deleteElement;
    }


    public int top() {
        return deque.getLast();
    }

    public int peekMax() {
        return maxHeap.peek();
    }

    public int popMax() {
        int deleteElement = maxHeap.poll();
        deque.removeLastOccurrence(deleteElement);
        return deleteElement;
    }
}
