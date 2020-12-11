package round2.heap_stack_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 12/8/2020 11:13 PM
 * @topic round2.heap_stack
 * @link https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * @description
 */
public class L150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
        Stack<String> stack = new Stack<>();
        for (String s: tokens) {
            if (!operators.contains(s)) {
                stack.push(s);
            } else {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+" -> stack.push(String.valueOf(first + second));
                    case "-" -> stack.push(String.valueOf(first - second));
                    case "*" -> stack.push(String.valueOf(first * second));
                    case "/" -> stack.push(String.valueOf(first / second));
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
