package round1;

public class L130_surroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;

        int row = board.length;
        int col = board[0].length;

        // check the first and last column for 'O' and mark all connecting 'O' with '*'
        for (int i = 0; i < row; i++) {
            dfs(board, i , 0);
            dfs(board, i, col -1);
        }
        // check the first and last row for 'O' and mark all connecting 'O' with '*'
        for (int j = 0; j < col; j++) {
            dfs(board, 0, j);
            dfs(board, row - 1, j);
        }
        // make all remaining 'O' to 'X'
        for (int i = 0; i < row ;i ++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '*') board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i > board.length - 1  || j > board[0].length - 1 || board[i][j] != 'O') return;
        board[i][j] = '*';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

    public static void main(String[] args) {
        L130_surroundedRegions solution = new L130_surroundedRegions();
        char[][] board = {{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
        solution.solve(board);
        for (char[] line : board) {
            for (char c : line) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
