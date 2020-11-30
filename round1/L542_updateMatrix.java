package round1;
/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
 */
import java.util.*;

public class L542_updateMatrix {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length ==0 || matrix[0].length == 0) return null;
        Queue<int[]> queue = new LinkedList<>();

        int row = matrix.length;
        int col = matrix[0].length;
        for (int i =0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        int minLen = 1;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int[] dir : directions) {
                int i = cur[0] + dir[0];
                int j = cur[1] + dir[1];
                if (i >=0 && j>=0 && i < row && j < col && matrix[i][j] > matrix[cur[0]][cur[1]] + 1) {
                    queue.offer(new int[]{i, j});
                    matrix[i][j] = matrix[cur[0]][cur[1]] + 1;
                }
            }

        }
        return matrix;

    }
}
