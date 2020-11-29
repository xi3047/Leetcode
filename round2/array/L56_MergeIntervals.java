package round2.array;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/28/2020 3:40 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/merge-intervals/
 * @description
 */
public class L56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) return new int[][]{};
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> list = new LinkedList<>();
        int[] prev = null;
        for (int[] cur : intervals) {
            if (prev == null) {
                prev = cur;
            } else if (prev[1] >= cur[0]) {
                prev = new int[]{prev[0], Math.max(prev[1], cur[1])};
            } else {
                list.add(prev);
                prev = cur;
            }
        }
        list.add(prev);
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
