package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-03-10
    @time:   00:52

    Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
 */
import org.junit.Test;

public class L44_WildcardMatching {
    // Solution: 2D Dynamic Programming
    // Credits: https://www.youtube.com/watch?v=3ZDZ-N0EPV0&t=846s
    public boolean isMatch_2d_method(String s, String p) {
        int m=s.length(), n=p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        // set initial match to true since empty pattern matches with empty string
        dp[0][0] = true;

        // when first letter is matched with empty pattern, set all to false
//        for (int i=1; i<=m; i++) {
//            dp[i][0] = false;
//        }

        //if the first character in the pattern is *, then it matches the empty string, assuming there coule be multiple leading *s
        for(int j=1; j<=n; j++) {
            if(p.charAt(j-1)=='*'){
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if (p.charAt(j-1) == '?' || s.charAt(i-1) == p.charAt(j-1)) { // when last char in the pattern is either '?' or it is the same as the last character in the text
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*'){ // if the last value is '*', then either * could represent a 0 sequence (empty), OR
                    dp[i][j] = dp[i-1][j] || dp[i][j-1]; // when we take a value from the left, it means
                    // we are considering '*' as a 0 sequence, when we take from the top, we are treating
                    // the last char in the string as a part of '*' sequences
                }
            }
        }
        
        return dp[m][n];
    }

    @Test
    public void test() {
        String s = "xaylmz";
        String p = "x?y*z";
        System.out.println(isMatch_2d_method(s,p));
    }
}

