package round4.otherDataStructure;

public class L348_DesignTicTacToe {
    class TicTacToe {
        private int[] rows;
        private int[] cols;
        private int diagonal;
        private int antiDiagonal;
        private int n;
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
            this.n = n;
        }

        public int move(int row, int col, int player) {
            int toAdd = player == 1? 1: - 1;
            rows[row] += toAdd;
            cols[col] += toAdd;
            if (row == col) diagonal += toAdd;
            if (row + col == n - 1) antiDiagonal += toAdd;

            if (Math.abs(rows[row]) == n
            || Math.abs(cols[col]) == n
            || Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
                return player;
            }
            return 0;
        }

        /**
         * Variant, given a input move and player, determine if he has win or not
         * series of move count
         */
        public boolean isWin(int[][] board, int player, int row, int col) {
            board[row][col] = player;
            int n = board.length;
            int rows = 0, cols = 0, diagonal = 0, antiDiagonal = 0;
            for (int i = 0; i < n; i++) {
                // check col
                if (board[i][col] == player) {
                    cols++;
                }
                // check row
                if (board[row][i] == player) {
                    rows++;
                }
                if (board[i][i] == player) {
                    diagonal++;
                }
                if (board[i][n - 1 - i] == player) {
                    antiDiagonal++;
                }
            }
            return rows == n || cols == n || diagonal == n || antiDiagonal == n;
        }
    }

}
