package round2.matrix;

/**
 * @author Xi Zhang
 * @date 12/20/2020 11:25 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/rotate-image/
 * @description
 * Solution explanation:
 * https://www.youtube.com/watch?v=SA867FvqHrM
 * Do a transpose along diagonal then flip the matrix horizontally
 */
public class L48_RotateImage {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][N - 1 - j];
                matrix[i][N - 1 - j] = temp;
            }
        }
    }
    public int[][] rotate2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return matrix;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] res = new int[cols][rows];
        int newCol = rows - 1;
        for (int i = 0; i < rows; i++) {
            int newRow = 0;
            for (int j = 0; j < cols; j++) {
                // last row -> rows - 1, col+
                res[newRow][newCol] = matrix[i][j];
                newRow++;
            }
            newCol--;
        }
        return res;


    }
}
