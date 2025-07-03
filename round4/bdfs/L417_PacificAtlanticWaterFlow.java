package round4.bdfs;

import java.util.*;

public class L417_PacificAtlanticWaterFlow {
    int row;
    int col;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if(heights.length == 0 || heights[0].length == 0) return result;
        row = heights.length;
        col = heights[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        // add all pacific
        addAllPacific(queue, heights, pacific);
        bfs(heights, result, queue, pacific, atlantic);
        // add all atlantic
        addAllAtlantic(queue, heights, atlantic);
        bfs(heights, result, queue, atlantic, pacific);

        return result;
    }

    private void addAllAtlantic(Queue<int[]> queue, int[][] heights, boolean[][] atlantic) {
        // right col
        for (int i = 0; i < row; i++) {
            queue.offer(new int[]{i, col - 1});
            atlantic[i][col - 1] = true;
        }
        // bottom row
        for (int j = 0; j < col - 1; j++) {
            queue.offer(new int[]{row - 1, j});
            atlantic[row - 1][j] = true;
        }
    }

    private void addAllPacific(Queue<int[]> queue, int[][] heights, boolean[][] pacific) {
        // top row
        for (int j = 0; j < col; j++) {
            queue.offer(new int[]{0, j});
            pacific[0][j] = true;
        }
        // left col
        for (int i = 1; i < row; i++) {
            queue.offer(new int[]{i , 0});
            pacific[i][0] = true;
        }
    }

    private void bfs(int[][] heights, List<List<Integer>> result, Queue<int[]> queue, boolean[][] self, boolean[][] other) {
        int[][] directions = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            if (other[i][j]) result.add(Arrays.asList(i, j));
            for (int[] direction : directions) {
                int x = i + direction[0];
                int y = j + direction[1];
                if (x >= 0 && x < row && y >= 0 && y < col && !self[x][y] && heights[x][y] >= heights[i][j]) {
                    self[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }
}
