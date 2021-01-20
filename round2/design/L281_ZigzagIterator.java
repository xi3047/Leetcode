package round2.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 1/5/2021 10:48 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/zigzag-iterator/
 * @description
 * Given two 1d vectors, implement an iterator to return their elements alternately.

 * Example:
 *
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
 */
public class L281_ZigzagIterator {
    Queue<Iterator> queue;
    public L281_ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<>();
        if (!v1.isEmpty()) queue.offer(v1.iterator());
        if (!v2.isEmpty()) queue.offer(v2.iterator());
    }

    public int next() {
        Iterator cur = queue.poll();
        int res = (int) cur.next();
        if (cur.hasNext()) queue.offer(cur);
        return res;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
