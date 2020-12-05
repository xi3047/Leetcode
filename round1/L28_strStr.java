package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-08
    @time:   15:02

    Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty round1_misc.string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty round1_misc.string. This is consistent to C's strstr() and Java's indexOf().
 */
public class L28_strStr {
    // Solution 1: O(m * n)c
    public int strStr(String l, String s) {
        if (l.length() == 0 && s.length() == 0) return 0;
        if (s == null || s.length() == 0) return 0;
        if (l == null || l.length() == 0) return -1;
        if (s.length() > l.length()) return -1;


        for (int i = 0; i < l.length() - s.length() + 1; i++) {
            int j = 0;
            for ( ;j < s.length(); j++) {
                if (l.charAt(i + j) != s.charAt(j)) break;
            }
            if (j == s.length()) {
                return i;
            }
        }
        return -1;
    }

    // Solution 2: Rabin-Karp algorithm, use hashcode to compare the two strings
    // Time: O(n + m)

    @Test
    public void test() {
        System.out.println(strStr("mississippi", "issipi"));
    }
}
