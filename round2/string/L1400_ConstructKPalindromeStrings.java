package round2.string;

/**
 * @author Xi Zhang
 * @date 12/21/2020 9:32 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/construct-k-palindrome-strings/
 * @description
 * Uber interview questions
Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.

Return True if you can use all the characters in s to construct k palindrome strings or False otherwise.

Example 1:

Input: s = "annabelle", k = 2
Output: true
Explanation: You can construct two palindromes using all characters in s.
Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
Example 2:

Input: s = "leetcode", k = 3
Output: false
Explanation: It is impossible to construct 3 palindromes using all the characters of s.
Example 3:

Input: s = "true", k = 4
Output: true
Explanation: The only possible solution is to put each character in a separate string.
Example 4:

Input: s = "yzyzyzyzyzyzyzy", k = 2
Output: true
Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
Example 5:

Input: s = "cr", k = 7
Output: false
Explanation: We don't have enough characters in s to construct 7 palindromes.
 */
public class L1400_ConstructKPalindromeStrings {
    /**
     *  Intuition：
     *  For a string to be a palindrome, they are only two cases
     *  one with even number of characters e.g. abba
     *  one with an even and on odd number e.g. abCba, ab X Y ba,
     *  so every odd occurrence character will need to be in their own string
     *  e.g. a b X Y b a have two odd characters X Y, if k is 1,
     *  we cannot construct only 1 palindrome, since X and Y need to be in different strings
     */
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;
        int[] count = new int[26];

        for (int c: s.toCharArray()) {
            count[c-'a']++;
        }
        int odd = 0;
        for (int c : count) {
            if (c %2 == 1) odd++;
        }

        return odd <= k;
    }
}
