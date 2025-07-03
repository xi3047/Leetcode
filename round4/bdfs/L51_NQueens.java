package round4.bdfs;

import java.util.ArrayList;
import java.util.List;

public class L51_NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        solveDFS(board, 0, res);
        return res;
    }

    private void solveDFS(char[][] board, int col, List<List<String>> res) {
        if (col == board.length) {
            deepCopy(board, res);
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                solveDFS(board, col + 1, res);
                board[row][col] = '.';
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;
        // check current row
        for (int j = 0; j < n; j++) {
            if (board[row][j] == 'Q') return false;
        }
        // check top right
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // check bottom right
        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // check top left
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // check bottom left
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private void deepCopy(char[][] board, List<List<String>> res) {
        List<String> curBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            curBoard.add(new String(board[i]));
        }
        res.add(curBoard);
    }


}
