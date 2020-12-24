package round2.bdfs;

/**
 * @author Xi Zhang
 * @date 12/12/2020 1:07 AM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/max-area-of-island/
 * @description
 */
public class L695_MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int[] maxArea = new int[]{0};
        int max = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, maxArea, m, n, i, j);
                    max = Math.max(max, maxArea[0]);
                    maxArea[0] = 0;
                }
            }
        }
        return max;
    }

    private void dfs(int[][] grid, int[] maxArea, int m, int n, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return;
        grid[i][j] = 0;
        maxArea[0] += 1;
        dfs(grid, maxArea, m , n , i - 1, j);
        dfs(grid, maxArea, m , n , i + 1, j);
        dfs(grid, maxArea, m , n , i, j - 1);
        dfs(grid, maxArea, m , n , i, j + 1);
    }

    public static void main(String[] args) {
        int size = 2;
        while (--size> 0){
            System.out.println(size);
        }
    }
}
