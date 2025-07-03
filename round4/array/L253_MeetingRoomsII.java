package round4.array;

import java.util.Arrays;

public class L253_MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int count = 0;
        int res = 0;
        int i = 0, j = 0;
        while (i < n) {
            if (start[i] < end[j]) {
                count++;
                res = Math.max(count ,res);
                i++;
            } else {
                count--;
                j++;
            }
        }
        return res;
    }
}
