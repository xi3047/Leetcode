package round4.bdfs;

import java.util.LinkedList;
import java.util.Queue;

public class L1091_ShortestPathinBinaryMatrix {
    static final int[][] directions = new int[][]{
            {-1, -1}, {0, -1}, {1, -1},
            {-1, 0},         {1, 0},
            {-1, 1}, {0, 1}, {1, 1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n-1][n-1] != 0) return -1;
        int len = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        grid[0][0] = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int i = cur[0];
                int j = cur[1];
                if (i == n - 1 && j == n -1) return len;
                grid[i][j] = 1;
                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            len++;
        }
        return -1;
    }

}
