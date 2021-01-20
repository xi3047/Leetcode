package round2.oa.airbnb_shengqiu;/*
Assumption:
1. The input is a list of string, each string store one row of the board
 the first character of each element is the type of land. the second character is the point
 the score for one type of land is the total are * total point
2. return the total score for this board
3. input is not null and a m*n matrix
4. non-connected land of same type need to calculate seperatelly

Approach:
The high level idea is start from each unvisited point on the grid, search for the area and total points,
calculate the score for this type of land, Then start from another unvisited point, do the same,
after all points are visited, we have total score.

Time: O(m*n)
that is the size of matrix
Space: O(m*n)
A visited set of O(mn), depth of recursion tree at most O(n*n)
====================================
 */

public class BoardScore {
    private int[] dirX = {0, 0, -1, 1};
    private int[] dirY = {-1, 1, 0, 0};

    public static void main(String[] args) {
        String[] input = {"S0 W1 W1 W0 L2",
                "W0 W0 T0 T0 T0",
                "W0 W1 T0 M2 M1",
                "S0 L0 S1 S0 S0",
                "M0 R2 R0 S1 T0"};
        BoardScore sol = new BoardScore();
        System.out.println(sol.getScore(input));
    }

    public int getScore(String[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }
        int row = input.length;
        int col = input[0].split(" ").length;
        String[][] grid = new String[row][col];
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            String[] temp = input[i].split(" ");
            for (int j = 0; j < col; j++) {
                grid[i][j] = temp[j];
            }
        }
        int score = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j]) {
                    int[] point = new int[]{0};
                    int[] area = new int[]{0};
                    dfs(grid, visited, i, j, grid[i][j].charAt(0), area, point);
                    score += point[0] * area[0];
                }
            }
        }
        return score;
    }

    private void dfs(String[][] grid, boolean[][] visited, int i, int j, char type, int[] area, int[] point) {
        if (!inBound(grid, i, j) || visited[i][j] || grid[i][j].charAt(0) != type) {
            return;
        }
        visited[i][j] = true;
        point[0] += grid[i][j].charAt(1) - '0';
        area[0]++;
        for (int k = 0; k < 4; k++) {
            dfs(grid, visited, i + dirX[k], j + dirY[k], type, area, point);
        }
    }

    private boolean inBound(String[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
