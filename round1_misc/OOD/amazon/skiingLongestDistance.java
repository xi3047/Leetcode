package round1_misc.OOD.amazon;
/*
    @author: Xi Zhang
    @date:   2019-05-15
    @time:   15:28

    Amazon Onsite 地里面经 https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=524884&highlight=amazon
    滑雪道最长路径
 */
import org.junit.Test;

public class skiingLongestDistance {
    public int longestDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null | grid[0].length ==0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        int[][] mem = new int[rows][cols];
        for (int j = 0; j < cols; j++) {
             max = Math.max(max, dfs(grid, 0, j, new boolean[rows][cols], mem));
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited, int[][] mem) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {1, 1} , {-1, 1}}; // need to deduplicate

        int max = 0;
        // failed case, out of bounds or obstacle or visited
        if (!isValid(grid, i, j , visited)) return 0;

        if (mem[i][j] != 0) {
            return mem[i][j];
        }

        // successful case: last row
        if ((i == rows - 1 && grid[i][j] != 1) && (j == 0 || j == cols - 1)) {
            mem[i][j] = 1;
            return 1;
        }

        visited[i][j] = true;
        for (int[] direction : directions) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            max = Math.max(max, dfs(grid, nextI, nextJ ,visited, mem) + 1);
        }
        visited[i][j] = false;
        mem[i][j] = max;
        return max;
    }

    private boolean isValid(int[][] grid, int i, int j, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 1 || visited[i][j]) return false;
        return true;
    }

    @Test
    public void test () {
        int[][] grid = {{0, 0, 0}, {1, 0, 0}, {0, 0, 1}};
        System.out.println(longestDistance(grid));
    }


}
