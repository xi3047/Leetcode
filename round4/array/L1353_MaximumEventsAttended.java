package round4.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class L1353_MaximumEventsAttended {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int count = 0, i = 0, lastDay = 0;
        for (int[] event :events) {
            lastDay = Math.max(lastDay, event[1]);
        }

        for (int day = 1; day <= lastDay; day++) {
            // keep inserting days
            while (i < events.length && events[i][0] == day) {
                minHeap.offer(events[i][1]);
                i++;
            }
            // remove expired days
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }
            // attend day ends earliest
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                count++;
            }

        }
        return count;
    }
}
