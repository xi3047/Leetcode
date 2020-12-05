package round1;
import java.util.*;

public class L253_MeetingRoom2 {
    class EndPoint implements Comparable<EndPoint> {
        int val;
        boolean isStart;

        public EndPoint(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(EndPoint other) {
            if (this.val != other.val) {
                return this.val - other.val;
            } else {
                return this.isStart? 1 : -1;
            }
        }
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        List<EndPoint> endPoints = new LinkedList<>();
        for (Interval itv : intervals) {
            endPoints.add(new EndPoint(itv.start, true));
            endPoints.add(new EndPoint(itv.end, false));
        }
        Collections.sort(endPoints);
        int count = 0;
        int roomsNeeded = 0;
        for (EndPoint endPoint : endPoints) {
            if (endPoint.isStart) count++;
            else {
                count--;
            }
            roomsNeeded = Math.max(roomsNeeded, count);
        }
        return roomsNeeded;
    }

    // Solution 2: using minHeap
    public int minMeetingRooms2(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a,  b) -> a.start - b.start);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i].end);
        }
        return minHeap.size();

    }

    // May 10th, 2019
    // new method signature
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);


        return 0;
    }
}
