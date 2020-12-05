package round1_misc.amazon;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class kthLargestIsland {
    public int kthLargestIsland(int[][] grid, int k) {
        // cc
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; i < col; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    queue.offer(new int[]{i, j});
                    int area = markIslandGetArea(grid, queue, visited);
                    minHeap.offer(area);
                    if (minHeap.size() > k) {
                        minHeap.poll();
                    }
                }
            }
        }
        return minHeap.peek();


    }

    private int markIslandGetArea(int[][] grid, Queue<int[]> queue, boolean[][] visited) {
        int row = grid.length;
        int col = grid[0].length;
        int area = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            area++;
            for (int[] dir : directions) {
                int newX = cur[0] + dir[0];
                int newY = cur[1] + dir[1];
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 1 && !visited[newX][newY]) {
                    queue.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                    area++;
                }
            }
        }
        return area;
    }
}
