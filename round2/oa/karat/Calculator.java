package round2.oa.karat;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 1/26/2021 7:37 PM
 * @topic round2.oa.karat
 * @link
 * @description
 */
public class Calculator {
    /*
    + - ( )
     */
    public int calculator1(String s) {
        int num = 0;
        int sign = 1;
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '+'){
                sum += num * sign;
                sign = 1;
                num = 0;
            } else if (c == '-') {
                sum += num * sign;
                sign = -1;
                num = 0;
            } else if (c == '(') {
                stack.push(sum);
                stack.push(sign);
                sum = 0;
                sign = 1;
            } else if (c == ')') {
                sum += num * sign;
                num = 0;
                sum = sum * stack.pop() + stack.pop();
                sign = 1;
            }
        }
        sum += num * sign;
        return sum;
    }

    /*
    Leetcode 227, Basic calculator 2
    + - * /
     */
    public int calculator2 (String s){
        Stack<Integer> stack = new Stack<>();
        int num = 0, result = 0;
        char lastSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (lastSign == '+') {
                  stack.push(num);
                } else if (lastSign == '-') {
                    stack.push(-num);
                } else if (lastSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (lastSign == '/') {
                    stack.push(stack.pop() / num);
                }
                lastSign = c;
                num = 0;
            }
        }
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }


    /*
    Leetcode 772, Basic Calculator 3
    + - * / ( ) and " "
     */
    public int calculator4(String s) {
        return 0;
    }


    @Test
    public void test1() {
        Assert.assertEquals(calculator1("3 - 5 + 4"), 2);
        Assert.assertEquals(calculator1("1 + 2 - 5"), -2);
    }
}
