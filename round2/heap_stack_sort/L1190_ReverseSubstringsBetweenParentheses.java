package round2.heap_stack_sort;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 12/19/2020 5:07 PM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * @description
 *
 * You are given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 *
 * Example 1:
 *
 * Input: s = "(abcd)"
 * Output: "dcba"
 * Example 2:
 *
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 * Example 3:
 *
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 * Example 4:
 *
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 *
 *         0 1 2 3 4 5 6 7 8 9
 *         u ( l o v e ) i

 */
public class L1190_ReverseSubstringsBetweenParentheses {
    /**
     * Leetcode 1ms Solution
     * Keep a char array and only insert characters into them
     * Use a stack to store supposed position of left brackets in the char array
     *
     *
     */
    public String reverseParentheses(String s) {
        Stack<Integer> opened = new Stack<>();
        char[] chars = new char[s.length()];
        int size = 0;

        for(int i=0; i<s.length(); ++i) {
            char c = s.charAt(i);
            // keep left boundary of inner most string in a stack
            if(c == '(') {
                opened.push(size);
            } // reverse the innermost string from the latest left boundary of stack to size - 1
            else if(c == ')') {
                reverse(chars, opened.pop(), size-1);
            }
            else { // if we see a character, add to char array and increase size
                chars[size++] = c;
            }
        }

        return new String(chars, 0, size);
    }

    private void reverse(char[] chars, int from, int to) {
        while(from < to) {
            char c = chars[from];
            chars[from] = chars[to];
            chars[to] = c;
            from++;
            to--;
        }
    }

    @Test
    public void test() {
        System.out.println(reverseParentheses("(u(love)i)"));
    }
}
