package leetcode;
/*
    @author: Xi Zhang
    @date:   2/6/19
    @time:   7:28 PM
    先用每个interval的start点进行排序
 */
import java.util.*;

public class L56_MergeIntervals {
    // solution 1, sort the start point first
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new LinkedList<>();
        if (intervals == null || intervals.size() == 0) return res;
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        Interval prev = null;
        for (Interval cur : intervals) {
            if (prev == null) prev = cur;
            else if (prev.end >= cur.start) {
                prev = new Interval(prev.start, Math.max(prev.end, cur.end));
            } else {
                res.add(prev);
                prev = cur;
            }
        }
        res.add(prev);
        return res;
    }

    public List<Interval> mergeInterval(List<Interval> intervals) {
        if (intervals.size() < 2) return intervals;
        List<Interval> res = new LinkedList<Interval>();
        int prevStart = intervals.get(0).start;
        int prevEnd = intervals.get(0).end;
        intervals.sort((a, b) -> a.start - b.start);

        for (Interval interval : intervals) {
            if (interval.start <= prevEnd) {
                prevEnd = Math.max(prevEnd, interval.end);
            } else {
                res.add(new Interval(prevStart, prevEnd));
                prevStart = interval.start;
                prevEnd = interval.end;
            }
        }
        res.add(new Interval(prevStart, prevEnd));
        return res;
    }

    // May 10th, 2019
    // new method signature
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) return new int[][]{};
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] prev = null;
        List<int[]> res = new ArrayList<>();
        for (int[] cur : intervals) {
            if (prev == null) prev = cur;
            else if (prev[1] >= cur[0]) { // overlap
                prev = new int[]{prev[0], Math.max(prev[1], cur[1])};
            } else { // not overlapping
                res.add(prev);
                prev = cur;
            }
        }
        res.add(new int[]{prev[0], prev[1]});
        int[][] arr = new int[res.size()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }


}

