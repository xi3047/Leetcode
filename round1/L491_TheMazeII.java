package round1;

public class L491_TheMazeII {
    static final int[] DIRS = {0, 1, 0, -1, 0};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] dist = new int[maze.length][maze[0].length];
        dist[start[0]][start[1]] = 1;
        dfs(start[0], start[1], destination, maze, dist);
        return dist[destination[0]][destination[1]] - 1;
    }
    void dfs(int row, int col, int[] dest, int[][] maze, int[][] dist) {
        if (row == dest[0] && col == dest[1]) return;
        int m = maze.length, n = maze[0].length;
        for (int d = 0; d < 4; ++d) {
            int i = row, j = col, p = DIRS[d], q = DIRS[d + 1];
            int len = dist[row][col];
            while (i + p >= 0 && i + p < m && j + q >= 0 && j + q < n && maze[i + p][j + q] == 0) {
                i += p;
                j += q;
                len++;
            }
            // 如果有走过 而且 当前走过的距离大于等于走过的，就跳过
            if (dist[i][j] > 0 && len >= dist[i][j]) continue;
            dist[i][j] = len;
            dfs(i, j, dest, maze, dist);
        }
    }

    int[][] directions = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int shortestDistance2(int[][] maze, int[] start, int[] destination) {

        int[][] distance = new int[maze.length][maze[0].length];
        dfs(maze, start, destination, distance);
        return distance[destination[0]][destination[1]] -1;
    }

    private void dfs(int[][] maze, int[] start, int[] destination, int[][] distance) {
        if (start[0] == destination[0] && start[1] == destination[1]) return;
        int m = maze.length;
        int n = maze[0].length;
        for (int [] dir: directions) {
            int i = start[0], j = start[1];
            int len = distance[i][j];
            while (isValid(i + dir[0], j + dir[1], m, n, maze)) {
                i += dir[0];
                j += dir[1];
                len++;
            }
            if (distance[i][j] > 0 && distance[i][j] > len) continue;
            distance[i][j] = len;
            dfs(maze, new int[]{i, j}, destination, distance);
        }
    }

    private boolean isValid(int x, int y, int m, int n, int[][] maze) {
        if (x < 0 || x >= m || y < 0 || y >= n || maze[x][y] != 0) return false;
        return true;
    }
}
