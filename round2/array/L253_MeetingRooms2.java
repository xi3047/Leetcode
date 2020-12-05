package round2.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/28/2020 6:39 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L253_MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        List<Time> timeline = new ArrayList<>();
        for (int [] interval : intervals) {
            timeline.add(new Time(interval[0], true));
            timeline.add(new Time(interval[1], false));
        }
        Collections.sort(timeline, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                 if (o1.minute == o2.minute) {
                     if (!o1.isStart && o2.isStart) return -1;
                     else if (o1.isStart && !o2.isStart) return 1;
                 } else {
                     return o1.minute - o2.minute;
                 }
                return 0;
            }
        });
        int rooms = 0;
        int maxRoom = 0;
        for (Time t: timeline) {
            if (t.isStart) {
                rooms++;
                maxRoom = Math.max(maxRoom, rooms);
            }
            else rooms--;
        }
        return maxRoom;
    }

    class Time {
        int minute;
        boolean isStart;

        public Time (int min, boolean isStart) {
            minute = min;
            this.isStart = isStart;
        }
    }

    public static void main(String[] args) {
        int [][] intervals = new int [][] {{13,15},{1,13}};
        L253_MeetingRooms2 solution = new L253_MeetingRooms2();
        System.out.println(solution.minMeetingRooms(intervals));
    }
}
