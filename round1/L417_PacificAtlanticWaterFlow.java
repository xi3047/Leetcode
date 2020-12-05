package round1;
/*
    @author: Xi Zhang
    @date:   2/3/19
    @time:   10:53 PM
 */
import java.util.*;

public class L417_PacificAtlanticWaterFlow {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int row = matrix.length, col = matrix[0].length;
        boolean[][] pacific = new boolean [row][col];
        boolean[][] atlantic = new boolean [row][col];
        Queue<int[]> queue = new LinkedList<>();

        addAllPacific(queue, matrix, pacific);
        bfs(res, matrix, queue, pacific, atlantic);

        addAllAtlantic(queue, matrix, atlantic);
        bfs(res, matrix, queue, atlantic, pacific);
        return res;
    }

    public void bfs(List<int[]> res, int[][] matrix, Queue<int[]> queue, boolean[][] self, boolean[][] other) {
        int [][] directions = new int[][] {{1,0}, {-1,0}, {0, 1}, {0, -1}};
        int row = matrix.length, col = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1];
            if (other[i][j]) res.add(new int[]{i,j});
            for (int[] dir : directions) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x >= 0 && x < row && y >=0 && y < col && !self[x][y] && matrix[i][j] <= matrix[x][y]) {
                    queue.offer(new int[]{x, y});
                    self[x][y] = true;
                }
            }
        }
        return;
    }

    public void addAllPacific(Queue<int[]> queue, int[][] matrix, boolean[][] pacific) {
        int row = matrix.length, col = matrix[0].length;
        for (int j= 0; j<col; j++) {
            queue.offer(new int[]{0,j});
            pacific[0][j] = true;
        }
        if (row >=1) {
            for (int i = 1; i < row; i++) {
                queue.offer(new int[]{i,0});
                pacific[i][0] = true;
            }
        }

    }

    public void addAllAtlantic(Queue<int[]> queue, int[][] matrix, boolean[][] atlantic) {
        int row = matrix.length, col = matrix[0].length;
        for (int j=0; j<col; j++) {
            queue.offer(new int[]{row-1, j});
            atlantic[row-1][j] = true;

        }
        for (int i=0; i<row-1; i++) {
            queue.offer(new int[]{i,col-1});
            atlantic[i][col-1] = true;
        }
    }
}

