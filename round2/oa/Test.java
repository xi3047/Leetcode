package round2.oa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/18/2020 8:55 PM
 * @topic round2.oa
 * @link
 * @description
 */
public class Test {


    // An Inplace function to
    // rotate a N x N matrix
    // by 90 degrees in
    // anti-clockwise direction
    static void rotateAntiClockwise( int mat[][])
    {
        int N = mat.length;
        // Consider all squares one by one
        for (int r = 0; r < N / 2; r++) {
            // Consider elements in group
            // of 4 in current square
            for (int c = r; c < N - r - 1; c++) {
                // Store current cell in
                // temp variable
                // store top left
                int temp = mat[r][c];

                // Move value from right to top left
                // y's col same as x's row
                // col is moving left
                mat[r][c] = mat[c][N - 1 - r];

                // Move values from bottom to right
                mat[c][N - 1 - r]
                        = mat[N - 1 - r][N - 1 - c];

                // Move values from left to bottom
                mat[N - 1 - r][N - 1 - c] = mat[N - 1 - c][r];

                // Assign temp to left
                mat[N - 1 - c][r] = temp;
            }
            List<Integer> list = new ArrayList<>();
        }
    }
    //todo
    static void rotateClockwise( int mat[][])
    {
        int N = mat.length;
        // Consider all squares one by one
        for (int x = 0; x < N / 2; x++) {
            // Consider elements in group
            // of 4 in current square
            for (int y = x; y < N - x - 1; y++) {
                // Store current cell in
                // temp variable
                // store top left
                int temp = mat[x][y];

                // Move value from right to top left
                mat[x][y] = mat[y][N - 1 - x];

                // Move values from bottom to right
                mat[y][N - 1 - x]
                        = mat[N - 1 - x][N - 1 - y];

                // Move values from left to bottom
                mat[N - 1 - x][N - 1 - y] = mat[N - 1 - y][x];

                // Assign temp to left
                mat[N - 1 - y][x] = temp;
            }
        }
    }

    public static void main(String[] args)
    {
        int arr[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        rotateAntiClockwise(arr);
        for (int [] row: arr) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
