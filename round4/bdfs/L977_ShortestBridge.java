package round4.bdfs;

import java.util.LinkedList;
import java.util.Queue;

public class L977_ShortestBridge {
    private int rows, cols;
    private int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

    public int shortestBridge(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Find first island and mark it
        boolean found = false;
        for (int i = 0; i < rows && !found; i++) {
            for (int j = 0; j < cols && !found; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    found = true;
                }
            }
        }

        // Step 2: BFS to expand to the second island
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
               int[] cell = queue.poll();
               for (int[] dir : dirs) {
                   int x = cell[0] + dir[0];
                   int y = cell[1] + dir[1];
                   if (x >= 0 && x < rows && y >= 0 && y < cols) {
                       if (grid[x][y] == 1) {
                           return steps;
                       } else if (grid[x][y] == 0) {
                           grid[x][y] = -1;
                           queue.offer(new int[]{x, y});
                       }
                   }
               }
            }
            steps++;
        }
        return -1;
    }

    private void dfs(int[][] grid, int i, int j, Queue<int[]> queue) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = -1;
        queue.offer(new int[]{i, j});
        for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1], queue);
        }
    }
}
