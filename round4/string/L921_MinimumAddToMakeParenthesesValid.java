package round4.string;

import java.util.Stack;

public class L921_MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        // additional close brackets + unmatched open brackets
        Stack<Character> stack = new Stack<>();
        int close = 0;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                stack.push('(');
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    close++;
                }
            }
        }
        return close + stack.size();
    }

    public int min(String s) {
        int open = 0;   // unmatched '('
        int needed = 0; // unmatched ')'

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else { // c == ')'
                if (open > 0) {
                    open--; // match with an open
                } else {
                    needed++; // no open to match, need one
                }
            }
        }
        return open + needed;
    }
}
