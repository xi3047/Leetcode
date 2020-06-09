package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-05-30
    @time:   20:59

    Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
 */
public class L74_Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;
        int row = matrix.length, col = matrix[0].length;
        int start = 0, end = row * col - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int i = mid / col;
            int j = mid % col;
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] < target) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }
}
