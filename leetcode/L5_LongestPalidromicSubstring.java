package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-02-13
    @time:   15:27

    Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

    Example 1:

    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
    Example 2:

    Input: "cbbd"
    Output: "bb"
 */
public class L5_LongestPalidromicSubstring {

    // 2 pointer solution, Time O(n^2), Space O(1)
    // Idea: extend to left and right from every position in the string, save the max substring
    public static String LPS(String s) {
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            String s1= extend(s, i ,i);       // check for odd length palidromes
            String s2 = extend(s, i, i+1); // check for even length palidromes
            if (s1.length() > max.length()) max = s1;
            if (s2.length() > max.length()) max = s2;
        }
        return max;

    }

    private static String extend(String s, int i, int j) {
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j)) break;
            i--;
            j++;
        }
        return s.substring(i+1,j);
    }

    // dp solution, time O(n^2), space O(n^2)
    // Idea:
    public static String longestPalindrome(String s) {
        int n = s.length();
        String res = null;
        int palindromeStartsAt = 0, maxLen = 0;

        boolean[][] dp = new boolean[n][n];
        // dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome

        for(int i = n-1; i >= 0; i--) { // keep increasing the possible palindrome string
            for(int j = i; j < n; j++) { // find the max palindrome within this window of (i,j)

                //check if substring between (i,j) is palindrome
                dp[i][j] = (s.charAt(i) == s.charAt(j)) // chars at i and j should match
                        &&
                        ( j-i < 3  // if window is less than or equal to 3, just end chars should match
                                || dp[i+1][j-1]  ); // if window is > 3, substring (i+1, j-1) should be palindrome too

                //update max palindrome string
                if(dp[i][j] && (j-i+1 > maxLen)) {
                    palindromeStartsAt = i;
                    maxLen = j-i+1;
                }
            }
        }
        return s.substring(palindromeStartsAt, palindromeStartsAt + maxLen);
    }


    public static void main(String[] args) {
        String a = "babad";
        System.out.println(longestPalindrome(a));
    }
}

