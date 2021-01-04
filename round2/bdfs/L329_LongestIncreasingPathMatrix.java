package round2.bdfs;

/**
 * @author Xi Zhang
 * @date 1/1/2021 7:40 AM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * @description
 *
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class L329_LongestIncreasingPathMatrix {
    final static int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] memo = new int[row][col];
        int maxLen = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maxLen = Math.max(maxLen, helper(matrix, i, j, Integer.MIN_VALUE, memo));
            }
        }
        return maxLen;
    }

    private int helper(int[][] matrix, int i, int j, int prev, int[][] memo) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= prev) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int max = 0;
        for (int[] dir : directions) {
            int newI = dir[0] + i;
            int newJ = dir[1] + j;
            max = Math.max(max, helper(matrix, newI, newJ, matrix[i][j], memo)+ 1);
        }
        memo[i][j] = max;
        return max;

    }
}
