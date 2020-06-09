package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-02-18
    @time:   16:09
    Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */
public class L240_search2dMatrix2 {

    /* Solution1: starting from the bottom left corner, if the target is greater than the current cell,
     it means the target is greater than all of this col, so we move the col to the right,
     likewise, if the target is smaller than the current cell, it means all cells to the right can't be the one we
     are looking for, so we continue to search in upper rows.
     Time complexity: O(m+n), space O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;

        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (target > matrix[row][col]) col++;
            else if (target < matrix[row][col]) row--;
            else {
                return true;
            }
        }
        return false;

    }
}
