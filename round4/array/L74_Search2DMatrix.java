package round4.array;

public class L74_Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;
        int rows = matrix.length, cols = matrix[0].length;
        int start = 0, end = rows * cols - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int i = mid / cols;
            int j = mid % cols;
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] < target) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }

}
