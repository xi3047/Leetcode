package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-05-14
    @time:   14:14
 */
public class L348_Design_Tic_Tac_Toe {
    class TicTacToe {
        int[] rows;
        int[] cols;
        int diagonal;
        int antiDiagonal;

        public TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
        }

        public int move(int row, int col, int player) {
            int n = rows.length;
            int toAdd = player == 1? 1 : -1;

            rows[row] += toAdd;
            cols[col] += toAdd;
            if (row == col) {
                diagonal += toAdd;
            }
            if (row == n - 1 - col) {
                antiDiagonal += toAdd;
            }

            if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
                return player;
            }
            return 0;
        }
    }

    @Test
    public void test() {
        TicTacToe toe = new TicTacToe(3);
        toe.move(1, 1, 2);
        toe.move(0, 0, 2);
        System.out.println(toe.move(2, 0, 2));

    }

}
