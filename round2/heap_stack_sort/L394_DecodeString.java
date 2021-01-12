package round2.heap_stack_sort;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 1/8/21 11:24 AM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/decode-string/
 * @description Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 */
public class L394_DecodeString {
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (ch == '[') {
                intStack.push(num);
                strStack.push(cur);
                cur = new StringBuilder();
                num = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                int count = intStack.pop();
                cur.append(tmp.toString().repeat(count));
            } else { // is a character
                cur.append(ch);
            }
        }
        return cur.toString();
    }
    @Test
    public void test() {
        System.out.println(decodeString("3[a]2[bc]"));
    }
}
