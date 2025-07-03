package round4.bdfs;

import java.util.LinkedList;
import java.util.Queue;

public class L286_WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                for (int[] direction : directions) {
                    int newX = cur[0] + direction[0];
                    int newY = cur[1] + direction[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && rooms[newX][newY] == Integer.MAX_VALUE) {
                        rooms[newX][newY] = rooms[cur[0]][cur[1]] + 1;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
    }
}
