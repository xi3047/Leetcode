package round4.string;

import java.util.Stack;

public class L1209_RemoveAllAdjacentDuplicates {
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().ch == c) {
                stack.peek().count++;
                if (stack.peek().count == k) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(c, 1));
            }

        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair cur = stack.pop();
            for (int i = 0; i < cur.count; i++) {
                sb.append(cur.ch);
            }
        }
        return sb.reverse().toString();
    }

    class Pair {
        char ch;
        int count;

        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
}
