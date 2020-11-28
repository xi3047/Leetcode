package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class L490_TheMaze {

    int[][] dirs = new int[][]{ {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }

    private boolean dfs(int[][] maze, int[] p, int[] destination, boolean[][] visited) {
        if (visited[p[0]][p[1]]) {
            return false;
        }
        if (p[0] == destination[0] && p[1] == destination[1]) {
            return true;
        }
        visited[p[0]][p[1]] = true;
        for (int[] dir : dirs) {
            int i = p[0];
            int j = p[1];
            while (i >= 0 && i < maze.length && j >= 0 && j < maze[0].length && maze[i][j] == 0) {
                i += dir[0];
                j += dir[1];
            }
            i -= dir[0];
            j -= dir[1];
            if (dfs(maze, new int[]{i, j}, destination, visited)) {
                return true;
            }
        }
        return false;
    }


    public boolean hasPathBFS(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        queue.offer(new int[]{start[0], start[1]});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int x = cur[0];
                int y = cur[1];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0){  // 如果没出界，是空地的话 就往四个方向滚
                    x += dir[0];
                    y += dir[1];
                }
                x -= dir[0];  // 因为while loop 会出界
                y -= dir[1];
                if (!visited[x][y]) {
                    visited[x][y] = true;
                    if (x == destination[0] && y == destination[1]) return true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return false;
     }
}
