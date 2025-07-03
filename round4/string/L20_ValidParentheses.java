package round4.string;

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
        return stack.empty();
    }

    // {} [] ()
    private boolean isPair(char a, char b) {
        if (a == '{') return b == '}';
        else if (a == '[') return b == ']';
        else if (a == '(') return b == ')';
        return false;
    }
}
