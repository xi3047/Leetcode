package round4.string;

import java.util.Stack;

public class L394_DecodeString {
    public String decodeString(String s) {
        Stack<StringBuilder> strStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                strStack.push(cur);
                countStack.push(num);
                num = 0;
                cur = new StringBuilder();
            } else if (c == ']') {
                int count = countStack.pop();
                StringBuilder last = strStack.pop();
                last.append(cur.toString().repeat(count));
                cur = last;
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }
}
