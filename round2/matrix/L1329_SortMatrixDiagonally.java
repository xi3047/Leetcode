package round2.matrix;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Xi Zhang
 * @date 12/21/2020 1:25 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/sort-the-matrix-diagonally
 * @description
 */
public class L1329_SortMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        Map<Integer, PriorityQueue<Integer>> h = new HashMap<>();

        int row = mat.length, col = mat[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                h.putIfAbsent(i - j, new PriorityQueue<>());
                h.get(i - j).offer(mat[i][j]);
            }
        }
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = h.get(i - j).poll();
            }
        }
        return mat;
    }
}
