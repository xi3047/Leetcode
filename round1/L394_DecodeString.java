package round1;

import java.util.Stack;

/*
    @author: Xi Zhang
    @date:   2019-02-20
    @time:   19:33
    Given an encoded round1_misc.string, return it's decoded round1_misc.string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input round1_misc.string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class L394_DecodeString {
    public static String decodeString(String s) {
        if (s == null) return null;
        Stack<Integer> iS = new Stack<>();
        Stack<StringBuilder> lS = new Stack<>();
        int curVal = 0;
        for (Character ch : s.toCharArray()) {
            if (ch > '0' && ch < '9') {
                curVal = curVal*10 + (ch - '0');
            } else if (ch == '[') {
                iS.push(curVal==0?1:curVal);
                curVal = 0;
                lS.push(new StringBuilder());
            } else if (ch == ']') {
                int count = iS.pop();
                String str = iS.pop().toString();
                StringBuilder sb = new StringBuilder();
                while (count-- > 0) {
                    sb.append(str);
                }
                lS.peek().append(sb);
            } else { // letter
                lS.peek().append(ch);
            }
        }
        return lS.pop().toString();
    }

    public String decodeString2(String s) {
        if (s == null) return null;
        int len = s.length();
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> sbStack = new Stack<>();

        int curNum = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curNum = curNum * 10 + (c - '0');
            } else if (c == '[') {
                numStack.push(curNum);
                curNum = 0;
                sbStack.push(new StringBuilder());
            } else if (c == ']') {
                int count = numStack.pop();
                StringBuilder top = sbStack.pop();
                for (int j = 0; j < count; j++) {
                    sbStack.peek().append(top.toString());
                }
            } else {
                sbStack.peek().append(c);
            }
        }
        return sbStack.pop().toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
    }
}
