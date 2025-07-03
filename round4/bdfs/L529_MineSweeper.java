package round4.bdfs;

public class L529_MineSweeper {
    int[][] DIRS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
    };
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
        } else {
            dfs(board, row, col);
        }
        return board;
    }

    private void dfs(char[][] board, int row, int col) {
        int m = board.length, n = board[0].length;
        if (row < 0 || row > m - 1 || col < 0 || col > n - 1) return;
        if (board[row][col] != 'E') return;

        int mineCount = 0;
        for (int[] dir : DIRS) {
            int x = row + dir[0];
            int y = col + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'M') {
                mineCount++;
            }
        }
        if (mineCount != 0) {
            board[row][col] = (char) (mineCount + '0');
        } else {
            board[row][col] = 'B';
            for (int[] dir : DIRS) {
                dfs(board, row + dir[0], col + dir[1]);
            }
        }
    }
}
