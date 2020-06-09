package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-05-25
    @time:   14:59

 */
import java.util.PriorityQueue;

public class MedianFinder {
    private PriorityQueue<Integer> minH;
    private PriorityQueue<Integer> maxH;

    /** initialize your data structure here. */
    public MedianFinder() {
        maxH = new PriorityQueue<>( (a, b) -> b - a);
        minH = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if ((minH.size()==0)&&(maxH.size()==0)) minH.add(num);
        else if ((minH.size())>(maxH.size())) {
            if (num>minH.peek()) {
                maxH.add(minH.poll());
                minH.add(num);
            } else maxH.add(num);
        } else if ((minH.size())<(maxH.size())) {
            if (num<maxH.peek()) {
                minH.add(maxH.poll());
                maxH.add(num);
            } else minH.add(num);
        } else {
            if (num<maxH.peek()) maxH.add(num);
            else minH.add(num);
        }
    }

    public double findMedian() {
        if ((minH.size()==0)&&(maxH.size()==0)) return 0.0;
        if ((minH.size())>(maxH.size())) return (double)(minH.peek());
        if ((minH.size())<(maxH.size())) return (double)(maxH.peek());
        return ((double)(maxH.peek()+minH.peek()))/2.0;
    }


}
