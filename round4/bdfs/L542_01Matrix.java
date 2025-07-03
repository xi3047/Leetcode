package round4.bdfs;

import java.util.LinkedList;
import java.util.Queue;

public class L542_01Matrix
{
    /**
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     *
     * The distance between two cells sharing a common edge is 1.
     */
    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] res = new int[row][col];
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (mat[r][c] == 0) {
                    q.offer(new int[]{r, c});
                }
            }
        }
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                for (int[] direction : directions) {
                    int x = cur[0] + direction[0];
                    int y = cur[1] + direction[1];
                    if (x >= 0 && x < row && y >= 0 && y < col && mat[x][y] == 1 && res[x][y] == 0) {
                        res[x][y] = res[cur[0]][cur[1]] + 1;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return res;
    }
}
