package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-10
    @time:   21:58
 */
public class L289_GameOfLife {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = 0;
                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                for (int x = Math.max(0, i - 1); x < Math.min(m, i + 2); x++) {
                    for (int y = Math.max(0, j - 1); y < Math.min(n, j + 2); y++) {
                        lives += board[x][y] & 1;
                    }
                }
                lives -= board[i][j];
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    @Test
    public void test() {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
       System.out.println(arrayVisualizer.get2DIntArrayPrint(board));
       gameOfLife(board);
       System.out.println(arrayVisualizer.get2DIntArrayPrint(board));
    }

}
