package round2.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Xi Zhang
 * @date 11/28/2020 6:20 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L252_MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) return false;
        }
        return true;
    }

    /*
    Create a new int comparator,
    so it will catch result while sorting
     */
    public boolean canAttend(int[][] intervals) {
        try {
            Arrays.sort(intervals, new IntervalComparator());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[]o2) {
            if (o1[0] < o2[0] && o1[1] <= o2[0]){
                return -1;
            } else if (o1[0] > o2[0] && o1[0] >= o2[1]) {
                return 1;
            }
            throw new RuntimeException();
        }
    }
}
