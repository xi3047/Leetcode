package round1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-03-12
    @time:   00:13
    Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class L120_TriangeMinimumPathSum {

    // Solution 1: 1D DP, with O(n^2) space
    public int minimumTotal(int[][] matrix) {
        // cc
        int m = matrix.length;
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                matrix[i][j] = matrix[i][j] + Math.min(matrix[i + 1][j], matrix[i + 1][j + 1]);
            }
        }
        return matrix[0][0];

    }

    // 1D Dynamic Programming, O(n^2), using O(n) space
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1 ];
        for(int i = triangle.size() - 1; i >= 0; i--) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                A[j] = Math.min(A[j], A[j+1]) + triangle.get(i).get(j);
            }
        }
        return A[0];
    }

    // 1D DP In place
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        List<Integer> li = new ArrayList<>(triangle.get(n-1));
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                li.set(j, triangle.get(i).get(j) + Math.min(li.get(j), li.get(j + 1)));
            }
        }
        return li.get(0);
    }

    @Test
    public void test() {
        int[][] arr = {{2}, {3,4} ,{6,5,7},{4,1,8,3}};
        System.out.println(minimumTotal(arr));
    }
}
