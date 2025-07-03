package round4.bdfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L317_ShortestDistanceFromAllBuildings {
    public int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] distance = new int[m][n];
        int[][] reach = new int[m][n];
        int buildingCount = 0;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildingCount++;
                    Queue<int[]> queue = new LinkedList<>();
                    boolean[][] visited = new boolean[m][n];
                    queue.offer(new int[]{i, j});
                    int level = 0;

                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        level++;
                        for (int s = 0; s < size; s++) {
                            int[] curr = queue.poll();
                            for (int[] dir : dirs) {
                                int x = curr[0] + dir[0];
                                int y = curr[1] + dir[1];

                                if (x >= 0 && x < m && y >= 0 && y < n &&
                                        grid[x][y] == 0 && !visited[x][y]) {
                                    visited[x][y] = true;
                                    distance[x][y] += level;
                                    reach[x][y]++;
                                    queue.offer(new int[]{x, y});
                                }
                            }
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingCount) {
                    min = Math.min(min, distance[i][j]);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
