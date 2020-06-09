package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-04-25
    @time:   17:22

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6

 */
import org.junit.Test;

import java.util.Stack;

public class L85_MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int maxArea = 0;
        int[] row = new int[matrix[0].length];
        // Initialize first row
        for (int j = 0; j < row.length; j++) {
            row[j] = Character.getNumericValue(matrix[0][j]);
        }
        maxArea = Math.max(maxArea,largestRectangleAreaStack(row));
        // Go through second row to last, using largest rectangle histogram and dynamic programming to calculate max rec
        // area on every row
        for (int r = 1; r < matrix.length; r++) {
            for (int j = 0; j < row.length; j++) {
                row[j] = matrix[r][j] == '0' ? 0 : row[j]+ 1;
            }
            maxArea = Math.max(maxArea,largestRectangleAreaStack(row));
        }
        return maxArea;
    }

    private int largestRectangleAreaStack(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        stack.push(-1);
        for (int i = 0; i <= heights.length; ++i) {
            while (stack.peek() != -1 && (i == heights.length || heights[stack.peek()] >= heights[i]))
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        return maxArea;
    }

    @Test
    public void test() {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        char[][] matrix2 = {{'1'}};
        System.out.println(maximalRectangle(matrix2));
    }
}
