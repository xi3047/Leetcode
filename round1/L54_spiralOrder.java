package round1;
/*
    @author - Xi Zhang
    @date   - today
    @time   - now
 */

import java.util.*;

public class L54_spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new LinkedList<>();
    helper(res, 0, matrix.length, matrix[0].length, matrix );
    return res;
    }

    private void helper(List<Integer> res, int offset, int row, int col, int[][] matrix) {
        if (Math.min(row, col) == 0) return;
        for (int j = 0; j < col - 1; j++) {
            res.add(matrix[offset][offset + j]);
        }
        for (int i = 0; i < row - 1; i++) {
            res.add(matrix[offset + i][offset + row - 1]);
        }
        for (int j = 0; j < col -1; j++) {
            res.add(matrix[offset + row - 1][offset + col + 1 - j]);
        }
        for (int i = 0; i < row -1; i++) {
            res.add(matrix[offset + col - 1 - i][offset]);
        }
        helper(res, offset + 1, row - 2, col - 2, matrix);
    }

}
