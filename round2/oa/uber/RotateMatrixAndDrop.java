package round2.oa.uber;

/**
 * @author Xi Zhang
 * @date 12/21/2020 9:35 AM
 * @topic round2.oa.uber
 * @link
 * @description
 * In a square matrix, there exists wood blocks, empty and obstacles,
 * rotate the matrix 90 degree clockwise and all the wood blocks will fall on the other woodblocks or obstacles
 * Suppose '1' means wood, '0' means empty, '#' means block
 */
public class RotateMatrixAndDrop {
    public static void rotateDrop(char[][] matrix) {
        int N = matrix.length;

        // Drop to the right ==>
        for (int i = 0; i < N; i++) {
            int curWindow = 0;
            int j = 0;
            while (j < N) {
                if (matrix[i][j] == '1') {
                    curWindow++;
                } else if (matrix[i][j] == '0') {
                    if (curWindow > 0) {
                        matrix[i][j] = '1';
                        matrix[i][j - curWindow] = '0';
                    }
                } else { // an obstacle #
                    curWindow = 0;
                }
                j++;
            }
        }

        // Rotate second
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                char temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N / 2; j ++) {
                char temp = matrix[i][j];
                matrix[i][j] = matrix[i][N - 1 - j];
                matrix[i][N - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0'},
                {'0', '0', '1', '#'},
                {'#', '1', '0', '1'},
                {'0', '1', '#', '0'}
        };
        rotateDrop(matrix);
        for (char[] row: matrix) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
