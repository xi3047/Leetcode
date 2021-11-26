package round3.array;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author : Xi
 * @topic: array
 * @created : 11/23/2021, Tuesday, 13:37
 **/
public class L56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        LinkedList<int[]> res = new LinkedList<>();
        for (int[] interval : intervals) {
            if (res.isEmpty() || interval[0] > res.getLast()[1]) {
                res.add(interval);
            } else {
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
