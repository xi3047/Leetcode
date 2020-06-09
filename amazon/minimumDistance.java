package amazon;

import java.util.*;

/*
    @author: Xi Zhang
    @date:   2019-02-22
    @time:   22:07
    Amazon OA question
    Amazon Fresh Delivery

    Solution: 用BFS，把遍历过的cell标记为0,直到找到target(9)为止
 */
public class minimumDistance {
    public static int minimumDistance(int numRows, int numCols, List<List<Integer>> area) {

        // cc
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});
        area.get(0).set(0, 0);
        int minDistance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur =queue.poll();
                for (int[] dir : directions) {
                    int i = cur[0] + dir[0];
                    int j = cur[1] + dir[1];
                    if (i >= 0 && i < numRows && j >= 0 && j < numCols && area.get(i).get(j) != 0) {
                        if (area.get(i).get(j) == 9) return minDistance+1;
                        area.get(i).set(j, 0);
                        queue.offer(new int[]{i, j});
                    }
                }
            }
            minDistance++;
        }
        return -1;

    }
}
