package round2.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/25/2020 6:06 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/pascals-triangle/
 * @description
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class L118_PascalTriangle {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        res.add(new ArrayList<>());
        res.get(0).add(1);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j < i - 1; j ++ ) {
                List<Integer> lastRow = res.get(i - 2);
                int left = lastRow.get(j - 1);
                int right = lastRow.get(j);
                row.add(left + right);
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
}
