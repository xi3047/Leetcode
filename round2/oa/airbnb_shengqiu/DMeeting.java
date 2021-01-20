package round2.oa.airbnb_shengqiu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
Assumption:
1. The input is a list of interval
2. return the interval that all employee is free

Approach:
First we define a class Interval, store start and end time. Put all intervals on a list, sort the intervals
on the start time. Then we try to merge two consecutive intervals on the list, if they have intersection, which
is end >= start, then we just update the end to the second one's end time. If we can not merge, means there is
free time in between, that is end < start, we add this interval to result.

Time: O(NlogN) for sorting, O(n) for merge and get result list
Space: O(n) to store interval lists
 */
//public class DEmployeeFreeTime {
//
//    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
//        List<Interval> res = new ArrayList<>();
//        List<Interval> meetings = new ArrayList<>();
//        for (List<Interval> list : schedule) {
//            for (Interval meeting : list) {
//                meetings.add(meeting);
//            }
//        }
//        Collections.sort(meetings, (Interval o1, Interval o2) -> {
//            return Integer.compare(o1.start, o2.start);
//        });
//        // scan and find interval
//        Interval last = meetings.get(0);
//        for (int i = 1; i < meetings.size(); i++) {
//            Interval cur = meetings.get(i);
//            if (cur.start > last.end) { // if has free time
//                res.add(new Interval(last.end, cur.start));
//                last = cur;
//            } else if (cur.end > last.end) { // if has no free time
//                last.end = cur.end;
//            }
//        }
//        return res;
//    }
//
//    public List<Interval> merge(List<Interval> intervals) {
//        // Write your solution here.
//        // corner cases: intervals not null
//        LinkedList<Interval> merged = new LinkedList<>();
//        Collections.sort(intervals, (o1, o2) -> {
//            return Integer.compare(o1.start, o2.start);
//        });
//        for (Interval interval : intervals) {
//            if (merged.size() == 0 || interval.start > merged.getLast().end) {
//                merged.add(interval);
//            } else {
//                if (merged.getLast().end < interval.end) {
//                    merged.getLast().end = interval.end;
//                }
//            }
//        }
//        return merged;
//    }
//
//    class Interval {
//        int start;
//        int end;
//        public Interval(int start, int end) {
//            this.start = start;
//            this.end = end;
//        }
//    }
//}

public class DMeeting {
    public List<Interval> getFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                events.add(new Event(interval.start, 0));
                events.add(new Event(interval.end, 1));
            }
        }
        events.sort((a, b) -> {
            if (a.time == b.time) {
                return a.status - b.status;
            }
            return a.time - b.time;
        });

        int start = -1;
        int count = 0;
        for (Event event : events) {
            //if it's a start
            if (event.status == 0) {
                //free time ends
                if (count == 0 && start != -1) {
                    res.add(new Interval(start, event.time));
                }
                count++;
            } else {
                start = event.time;
                count--;
            }
        }
        return res;
    }

    //follow up: if we need find free time that >= k people are in rest

    public List<Interval> getFreeTimeII(List<List<Interval>> schedule, int k) {
        List<Interval> res = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        if (schedule.isEmpty()) {
            return res;
        }
        int totalEmployee = schedule.size();
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                events.add(new Event(interval.start, 0));
                events.add(new Event(interval.end, 1));
            }
        }
        if (events.isEmpty()) {
            return res;
        }
        events.sort((a, b) -> {
            if (a.time == b.time) {
                return a.status - b.status;
            }
            return a.time - b.time;
        });

        //for k = totalEmployee, which means previous problem, our start is first time we don't have any meeting
        //but for other k smaller than it, our start time is first meeting's start time
        int start = -1;
        if (k < totalEmployee) {
            start = events.get(0).time;
        }
        int numAtWork = 0;
        for (Event event : events) {
            //if it's a start
            if (event.status == 0) {
                // if adding current meeting will break the free time condition,
                // we add previous recorded free time
                if (start != -1 && numAtWork == totalEmployee - k) {
                    res.add(new Interval(start, event.time));
                }
                numAtWork++;
            } else {
                numAtWork--;
                if (numAtWork == totalEmployee - k) {
                    start = event.time;
                }
            }
        }
        res.add(new Interval(start, events.get(events.size() - 1).time));
        return res;
    }



    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    class Event {
        int time;
        //0 for start, 1 for end
        int status;

        public Event(int time, int status) {
            this.time = time;
            this.status = status;
        }
    }

    public static void main(String[] args) {
        List<List<Interval>> schedule = new ArrayList<>();
        List<Interval> p1 = new ArrayList<>();
        p1.add(new Interval(1, 3));
        p1.add(new Interval(4, 7));
        List<Interval> p2 = new ArrayList<>();
        p2.add(new Interval(2, 4));
        p2.add(new Interval(6, 8));
        List<Interval> p3 = new ArrayList<>();
        p3.add(new Interval(1, 2));
        List<Interval> p4 = new ArrayList<>();
        p4.add(new Interval(2, 8));
        schedule.add(p1);
        schedule.add(p2);
        schedule.add(p3);
        schedule.add(p4);
        DMeeting meeting = new DMeeting();
        List<Interval> res = meeting.getFreeTimeII(schedule, 2);
        for (Interval interval : res) {
            System.out.println("start : " + interval.start +"; end : " + interval.end);
        }

    }
}
