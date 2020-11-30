package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-11
    @time:   20:15
395. Longest Substring with At Least K Repeating Characters
Find the length of the longest substring T of a given round1_misc.string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class L395_LongestSubStrKRepeatingChar {
    public int longestSubstring(String s, int k) {
        int d = 0;

        for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++)
            d = Math.max(d, longestSubstringWithNUniqueChars(s, k, numUniqueTarget));

        return d;
    }

    private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget) {
        int[] map = new int[128];
        int numUnique = 0; // counter 1
        int numNoLessThanK = 0; // counter 2
        int begin = 0, end = 0;
        int d = 0;

        while (end < s.length()) {
            if (map[s.charAt(end)]++ == 0) numUnique++; // increment map[c] after this statement
            if (map[s.charAt(end++)] == k) numNoLessThanK++; // inc end after this statement

            while (numUnique > numUniqueTarget) {
                if (map[s.charAt(begin)]-- == k) numNoLessThanK--; // decrement map[c] after this statement
                if (map[s.charAt(begin++)] == 0) numUnique--; // inc begin after this statement
            }

            // if we found a round1_misc.string where the number of unique chars equals our target
            // and all those chars are repeated at least K times then update max
            if (numUnique == numUniqueTarget && numUnique == numNoLessThanK)
                d = Math.max(end - begin, d);
        }

        return d;
    }

    @Test
    public void test() {
        System.out.println(longestSubstring("ababbc", 2));

    }

}
