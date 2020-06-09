package leetcode;
/*
    @author: Xi Zhang
    @date:   2/1/19
    @time:   5:14 PM
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class L20_ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && isPair(stack.peek(), c)) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        return stack.isEmpty();
    }

    private boolean isPair(char a, char b) {
        if (a == '(' && b == ')') return true;
        if (a == '[' && b == ']') return true;
        if (a == '{' && b == '}') return true;
        return false;
    }


    public boolean isValid3(String s) {
        HashMap<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put(']', '[');
        mappings.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (mappings.containsKey(c)) {
                char top = stack.isEmpty()? '#' : stack.pop();
                if (top != mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }


    @Test
    public void test() {
    }
}
