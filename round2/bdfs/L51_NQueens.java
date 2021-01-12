package round2.bdfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/11/21 9:35 AM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/n-queens/
 * @description
 */
public class L51_NQueens {
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        solve(res, chess, 0);
        return res;
    }

    private void solve(List<List<String>> res, char[][] chess, int col) {
        // base case, if it is the last column
        if (col == chess[0].length) {
            // deep copy the chess board to the result list of boards
            res.add(construct(chess));
            return;
        }
        // I need to place 1 queen in this column, for every possible place in this column
        for (int row = 0; row < chess.length; row++) {
            // if this position is safe
            if (isSafe(chess, row, col)) {
                // choose and place the queen in this position
                chess[row][col] = 'Q';
                // explore the options in the next column
                solve(res, chess, col + 1 );
                // un-choose the previous position
                chess[row][col] = '.';
            }
        }
    }

    // because next placement is in the next column, we don't need to check for current column
    private boolean isSafe(char[][] chess, int curRow, int curCol) {
        // check current row
        for (int j = 0; j < chess.length; j++) {
            if (chess[curRow][j] == 'Q') {
                return false;
            }
        }
        // check 右上45度
        for (int i = curRow - 1, j = curCol + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        // check 右下45度
        for (int i = curRow + 1, j = curCol + 1; i < chess.length && j < chess.length; i++, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        // check 左下45度
        for (int i = curRow + 1, j = curCol - 1; i < chess.length && j >= 0; i++, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        // check 左上45度
        for (int i = curRow - 1, j = curCol - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> construct(char[][] chess) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            // add each row to the board
            board.add(new String(chess[i]));
        }
        return board;
    }
}
