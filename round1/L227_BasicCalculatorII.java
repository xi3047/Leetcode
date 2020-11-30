package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-05-02
    @time:   17:34
    The expression round1_misc.string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
public class L227_BasicCalculatorII {
    public int calculate(String s) {
        int len = s.length();
        int result = 0;
        int prevNum = 0;

        char prevOp = '+';

        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int val = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    val = val * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                if (prevOp == '+') {
                    result += val;
                    prevNum = val;

                } else if (prevOp == '-') {
                    result += val;
                    prevNum = -val;

                } else if (prevOp == '*') {
                    prevNum = prevNum * val;

                } else if (prevOp == '/') {
                    prevNum = prevNum / val;
                }
            } else if (s.charAt(i) != ' ') {
                prevOp = s.charAt(i);
            }
        }
        result += prevNum;
        return result;
    }

    public int calculate2(String s) {
        int res = 0;
        char sign = '+';
        for (int i = 0, num = 0, pre = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ("+-*/".indexOf(c) >= 0 || i == s.length() - 1) {
                if (sign == '+') {
                    pre = num;
                } else if (sign == '-') {
                    pre = -num;
                } else if (sign == '*') {
                    res -= pre;
                    pre *= num;
                } else {
                    res -= pre;
                    pre /= num;
                }
                sign = c;
                num = 0;
                res += pre;
            }
        }

        return res;
    }
    @Test
    public void test () {
        String s = "4+6/3*2";
        System.out.println(calculate2(s));
    }

}
