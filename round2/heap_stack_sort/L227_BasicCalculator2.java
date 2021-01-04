package round2.heap_stack_sort;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 12/28/2020 7:17 PM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/basic-calculator-ii/
 * @description
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 */
public class L227_BasicCalculator2 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
                if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = c;
                num = 0;
            }
        }

        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
    @Test
    public void test () {
        String s = "3+2*2";
        System.out.println(calculate(s));
    }

}
