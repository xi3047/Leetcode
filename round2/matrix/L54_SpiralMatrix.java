package round2.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/24/2020 7:14 PM
 * @topic round2.matrix
 * @link https://leetcode.com/problems/spiral-matrix/
 * @description
 */
public class L54_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int startRow = 0, endRow = matrix.length -1;
        int startCol = 0, endCol = matrix[0].length -1;
        int dir = 0;

        while (startRow <= endRow && startCol <= endCol) {
            switch(dir) {
                case 0: // goes right
                    for (int col = startCol; col <= endCol; col++)
                        result.add(matrix[startRow][col]);
                    startRow++;
                    break;
                case 1: // goes down
                    for (int row = startRow; row <= endRow; row++)
                        result.add(matrix[row][endCol]);
                    endCol--;
                    break;
                case 2: // goes left
                    for (int col = endCol; col >= startCol; col--)
                        result.add(matrix[endRow][col]);
                    endRow--;
                    break;
                case 3: // goes up
                    for (int row = endRow; row >= startRow; row--)
                        result.add(matrix[row][startCol]);
                    startCol++;
                    break;
            }
            dir = (dir + 1) % 4;
        }
        return result;
    }

}
