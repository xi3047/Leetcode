package round2.matrix;

/**
 * @author Xi Zhang
 * @date 11/27/2020 9:41 PM
 * @topic round2.matrix
 * @link
 * @description
 */
public class L59_SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int startRow = 0, endRow = n - 1;
        int startCol = 0, endCol = n - 1;
        int dir = 0;
        int val = 1;

        while (startRow <= endRow && startCol <= endCol) {
            switch(dir) {
                case 0: // goes right
                    for (int col = startCol; col <= endCol; col++)
                        matrix[startRow][col] = val++;
                    startRow++;
                    break;
                case 1: // goes down
                    for (int row = startRow; row <= endRow; row++)
                        matrix[row][endCol] = val++;
                    endCol--;
                    break;
                case 2: // goes left
                    for (int col = endCol; col >= startCol; col--)
                        matrix[endRow][col] = val++;
                    endRow--;
                    break;
                case 3: // goes up
                    for (int row = endRow; row >= startRow; row--)
                        matrix[row][startCol] = val++;
                    startCol++;
                    break;
            }
            dir = (dir + 1) % 4;
        }
        return matrix;
    }
}
