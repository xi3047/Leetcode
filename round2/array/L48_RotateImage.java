package round2.array;

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
}
