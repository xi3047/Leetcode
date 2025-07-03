package round4.bdfs;

import java.util.HashSet;
import java.util.Set;

public class L694_NumberOfDistinctIsland {
    public int numDistinctIslands(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 ) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i,j,sb, 'O');
                    set.add(sb.toString());
                    System.out.println(sb.toString());
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder path, char dir) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return;

        grid[i][j] = 0;
        path.append(dir);
        dfs(grid, i + 1, j, path, 'D');
        dfs(grid, i - 1, j, path, 'U');
        dfs(grid,  i, j + 1, path, 'R');
        dfs(grid, i, j - 1, path, 'L');
    }

    public static void main(String[] args) {
        L694_NumberOfDistinctIsland sol = new L694_NumberOfDistinctIsland();
        int[][] grid = {
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        System.out.println(sol.numDistinctIslands(grid));
    }
}
