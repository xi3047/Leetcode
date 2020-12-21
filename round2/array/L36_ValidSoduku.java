package round2.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xi Zhang
 * @date 12/17/2020 5:38 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/valid-sudoku
 * @description
 */
public class L36_ValidSoduku {
    public boolean isValidSudoku(char[][] board) {
        if (board == null) return false;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char number = board[i][j];
                if (number == '.') continue;
                if (!set.add(i + "row" + number) || !set.add(j + "col" + number)
                || !set.add(i/3 + "-" + j/3 + "block" + number)) {
                    return false;
                }
            }
        }
        return true;
    }
}
