package round2.bdfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/18/2021 12:34 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/expression-add-operators/
 * @description
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 */
public class L282_ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        backtrack(num, res, target, new StringBuilder(), 0, 0,0);
        return res;
    }

    private void backtrack(String num, List<String> res, int target, StringBuilder path, int index, int sum, int lastVal) {
        if (index == num.length() && sum == target) {
            res.add(path.toString());
            return;
        }
        if (index == num.length()) {
            return;
        }
        int val = 0;
        int len = path.length();
        for (int i = index; i < num.length(); i++) {
            if (val == 214748364 && (num.charAt(i) -'0') > 7) break;
            val = val * 10 + (num.charAt(i) - '0');
            // first character
            if (path.length() == 0) {
                path.append(val);
                backtrack(num, res, target, path, i + 1, val, val);
                path.setLength(len);
            } else {
                // +
                path.append("+" + val);
                backtrack(num, res, target, path, i + 1, sum + val, val);
                path.setLength(len);
                // -
                path.append("-" + val);
                backtrack(num, res, target, path, i + 1, sum - val, -val);
                path.setLength(len);
                // *
                path.append("*" + val);
                backtrack(num, res, target, path, i + 1, sum - lastVal + lastVal * val, lastVal * val);
                path.setLength(len);
            }
            // this means if current digit/val is 0, we stop make a number out of this
            if (val == 0) break;
        }
    }

    @Test
    public void test() {
        System.out.println(addOperators("2147483648", -2147483648));
    }
}
