package round4.array;

public class L498_DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int[] res = new int[rows * cols];

        int i = 0, j = 0;
        boolean goingUp = true;
        for (int cur = 0; cur < res.length; cur++) {
            res[cur] = mat[i][j];

            if (goingUp) {
                if (j == cols - 1) { // Hit the right boundary
                    i++;
                    goingUp = false;
                } else if (i == 0) { // Hit the top boundary
                    j++;
                    goingUp = false;
                } else {
                    i--;
                    j++;
                }
            } else {
                if (i == rows - 1) { // hits bottom
                    j++;
                    goingUp = true;
                } else if (j == 0) { // hits left
                    i++;
                    goingUp = true;
                } else {
                    i++;
                    j--;
                }
            }
        }
        return res;
    }
}
