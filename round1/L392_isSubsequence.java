package round1;

/*
    @author: Xi Zhang
    @date:   2019-02-24
    @time:   20:47

    Given a round1_misc.string s and a round1_misc.string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) round1_misc.string, and s is a short round1_misc.string (<=100).

A subsequence of a round1_misc.string is a new round1_misc.string which is formed from the original round1_misc.string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?


 */
public class L392_isSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int m = s.length();
        int n = t.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }
}
