package round4.bdfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L827_MakingALargeIsland {
    int[][] dirs = {{-1, 0} , {0, -1}, {1, 0}, {0 ,1}};
    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> idToArea = new HashMap<>();
        int n = grid.length;
        int id = 2;
        // step 1: find all island and their area and mark them with unique ID
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, id);
                    idToArea.put(id, area);
                    id++;
                }
            }
        }
        // step 2: for every 0 find if neighbors is island,
        // do not add same area twice, update max area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int area = 1;
                    Set<Integer> seen = new HashSet<>();
                    for (int[] dir: dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] > 1) {
                            int neighborId = grid[i][j];
                            if (seen.add(neighborId)) {
                                area += idToArea.get(neighborId);
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea == 0 ? n * n : maxArea;
    }

    private int dfs(int[][] grid, int i, int j, int id) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] != 1) return 0;
        int area = 1;
        grid[i][j] = id;
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            area += dfs(grid, x, y, id);
        }
        return area;
    }
}
/**
 * 2 0
 * 0 3
 */