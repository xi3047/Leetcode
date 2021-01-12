package round2.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 1/8/21 10:08 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/design-hit-counter/
 * @description
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 */
public class L362_HitCounter {
    /** Initialize your data structure here. */
    private Queue<Integer> hits;
    public L362_HitCounter() {
        hits = new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        hits.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!hits.isEmpty()) {
            int diff = timestamp - hits.peek();
            if (diff >= 300) hits.poll();
            else break;
        }
        return hits.size();
    }
}
