package round1;


import java.util.*;
public class L218_Skyline {
    /*
        @author: Xi Zhang
        @date:   2/6/19
        @time:   8:53 PM
        step 1: sort endpoint according to its x coordinate, if tie, use tie conditions
        step 2: scan endpoints, if left, add to pool, if highest draw
                                 else right, remove its left from pool, if highest draw

        BREAKING TIE:
        1. two same left, put the higher one first
        2. two same right, put the lower one first
        3. right paired with left, put the higher one first
        4. left paired with right, put the lower one first
        3.0 left and right, left first

        Time Complexity:
        T(n): n^2
        如果用treeset的话，但treeset不能handle duplicate，所以我们存个index，
     */

    class EndPoint implements Comparable<EndPoint> {
        int x, height;
        boolean isStart; //是左边的

        public EndPoint(int x, int h, boolean isStart) {
            this.x = x;
            this.height = h;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(EndPoint that) {
            if (this.x != that.x) {
                return this.x - that.x;
            }
            if (this.isStart && that.isStart) { //tie condition 1
                return that.height - this.height;
            } else if (!this.isStart && !that.isStart) { // tie condition 2
                return this.height - that.height;
            } else { // tie condition 3.0
                return this.isStart? -1 : 1;
            }
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new LinkedList<>();
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
            return res;
        }
        List<EndPoint> endPoints = new LinkedList<>();

        for (int[] b : buildings) {
            endPoints.add(new EndPoint(b[0], b[2], true));
            endPoints.add(new EndPoint(b[1], b[2], false));
        }
        Collections.sort(endPoints);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (EndPoint p : endPoints) {
            if (p.isStart) {
                if (maxHeap.isEmpty() || p.height > maxHeap.peek()) {
                    res.add(new int[]{p.x, p.height});
                }
                maxHeap.offer(p.height);
            } else {
                maxHeap.remove(p.height); // this take O(n) to find the target and O(logn) to remove
                if (maxHeap.isEmpty() || p.height > maxHeap.peek()) {
                    res.add(new int[]{p.x, maxHeap.isEmpty() ? 0 : maxHeap.peek()});
                }
            }
        }
        return res;
    }

}
