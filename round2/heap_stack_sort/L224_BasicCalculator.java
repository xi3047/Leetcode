package round2.heap_stack_sort;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 12/28/2020 5:42 PM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/basic-calculator/
 * @description
 * Given a string s representing an expression, implement a basic calculator to evaluate it.

 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 */
public class L224_BasicCalculator {
    public int calculate(String s) {
        int len = s.length(), sign = 1, num = 0, result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (c - '0');
            }
            else if (s.charAt(i) == '+') {
                result += sign * num;
                sign = 1;
                num = 0;
            } else if (s.charAt(i) == '-') {
                result += sign * num;
                sign = -1;
                num = 0;
            } else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result += sign * num;
                num = 0;
                sign = 1;
                result = result * stack.pop() + stack.pop();
            }
        }
        result += num * sign;
        return result;
    }

    @Test
    public void test() {
        System.out.println(calculate("- (3 + (4 + 5))"));
    }
}
