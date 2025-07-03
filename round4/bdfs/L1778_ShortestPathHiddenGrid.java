package round4.bdfs;

import java.util.*;

public class L1778_ShortestPathHiddenGrid {
    class GridMaster {
        boolean canMove(char direction) {
            return false;
        }
        void move(char direction) {

        }
        boolean isTarget() {
            return false;
        }
    }

    // Directions and helper data
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    String[] moveNames = {"R", "L", "D", "U"};

    // Grid representation
    Map<String, Boolean> visited = new HashMap<>();
    int targetX = -1, targetY = -1;

    public int findShortestPath(GridMaster master) {
        // Step 1: Explore the grid and build a map
        dfs(0, 0, master);
        if (targetX == -1 && targetY == -1) return -1;

        // Step 2: BFS to find the shortest path
        return bfs();
    }

    private void dfs(int x, int y, GridMaster master) {
        String key = x + "," + y;
        visited.put(key, true);
        if (master.isTarget()) {
            targetX = x;
            targetY = y;
        }

        for (int i = 0; i < 4; i++) {
            String dir = moveNames[i];
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            String nKey = nx + "," + ny;

            if (!visited.containsKey(nKey) && master.canMove(dir.charAt(0))) {
                master.move(dir.charAt(0));
                dfs(nx, ny, master);
                // Backtrack to previous cell
                master.move(opposite(dir));
            }
        }
    }

    // Opposite direction for backtracking
    private char opposite(String dir) {
        if (dir.equals("U")) return 'D';
        if (dir.equals("D")) return 'U';
        if (dir.equals("L")) return 'R';
        if (dir.equals("R")) return 'L';
        return ' ';
    }

    // BFS to find shortest path in the discovered grid
    private int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.add(new int[]{0, 0, 0});
        seen.add("0,0");

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], steps = curr[2];

            if (x == targetX && y == targetY) return steps;

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                String key = nx + "," + ny;
                if (visited.getOrDefault(key, false) && !seen.contains(key)) {
                    seen.add(key);
                    queue.add(new int[]{nx, ny, steps + 1});
                }
            }
        }
        return -1; // target not reachable
    }

}
