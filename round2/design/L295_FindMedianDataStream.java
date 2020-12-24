package round2.design;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Xi Zhang
 * @date 12/17/2020 6:08 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/find-median-from-data-stream/
 * @description
 */
public class L295_FindMedianDataStream {
    /** initialize your data structure here. */
    PriorityQueue<Integer> minH;
    PriorityQueue<Integer> maxH;
    public L295_FindMedianDataStream() {
        minH = new PriorityQueue<Integer>();
        /* By default Java provides min heap. For max heap, we need to pass in a appropriate comparator */
        maxH = new PriorityQueue<Integer>(0, new Comparator<Integer>(){
            public int compare(Integer ob1, Integer ob2){
                return ob2.compareTo(ob1);
            }
        });
    }

    public void addNum(int num) {
        maxH.add(num);
        minH.add(maxH.poll());
        if (minH.size() > maxH.size()) {
            maxH.add(minH.poll());
        }
    }

    public double findMedian() {
        if (minH.size() == maxH.size()) {
            return (double) (maxH.peek() + minH.peek()) * 0.5;
        } else {
            return (double) maxH.peek();
        }
    }
}
