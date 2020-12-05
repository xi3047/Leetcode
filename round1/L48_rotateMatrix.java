package round1;

/*
    @author: Xi Zhang
    @date:   1/25/19
    @time:   12:30 PM

    Rotate a n*n matrix by 90 clockwise in place

 */
public class L48_rotateMatrix {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0] == null) return;
        rotateHelper(matrix, 0, matrix.length);
    }

    private void rotateHelper(int[][] matrix, int offset, int size) {
        // base case
        if (size <= 1) return;

        for (int i = 0; i < size - 1; i++) {
            //cache the top-left element
            int temp = matrix[offset][offset + i];

            // move the bottom-left to top left
            matrix[offset][offset + i] = matrix[offset + size - 1 - i][offset];
            // move the bottom-right to bottom left
            matrix[offset + size - 1 - i][offset] = matrix[offset + size - 1][offset + size - 1 - i];
            // move the top right to bottom right
            matrix[offset + size - 1][offset + size - 1 - i] = matrix[offset + i][offset + size -1];
            // finally, move the cached element to the top right
            matrix[offset + i][offset + size - 1] = temp;
        }
        rotateHelper(matrix, offset + 1, size - 2);
    }

}
