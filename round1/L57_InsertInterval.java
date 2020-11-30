package round1;
/*
    @author: Xi Zhang
    @date:   2019-05-10
    @time:   21:11

    Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
import java.util.ArrayList;
import java.util.List;

public class L57_InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> result = new ArrayList<>();
        int i = 0;
        int start = newInterval.start;
        int end = newInterval.end;


        while (i < intervals.size() && intervals.get(i).end < start) {
            result.add(intervals.get(i++));
        }

        while (i < intervals.size() && intervals.get(i).start <= end) {
            start = Math.min(start, intervals.get(i).start);
            end = Math.max(end, intervals.get(i).end);
            i++;
        }
        result.add(new Interval(start,end));

        while (i < intervals.size()) result.add(intervals.get(i++));
        return result;
    }

    // new method signature
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int start = newInterval[0];
        int end = newInterval[1];

        // add non overlapping intervals before the new interval to result
        while (i < intervals.length && intervals[i][1] < start) {
            result.add(intervals[i]);
            i++;
        }
        // merge the overlapping intervals into one
        while (i < intervals.length && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        result.add(new int[]{start, end});
        // add the rest of the intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        // output from result list to result array
        int[][] arr = new int[result.size()][];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = result.get(j);
        }
        return arr;
    }
}
