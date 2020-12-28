package round2.dp;

/**
 * @author Xi Zhang
 * @date 12/25/2020 5:05 PM
 * @topic round2.dp
 * @link https://leetcode.com/problems/decode-ways/
 * @description
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 *
 * Input: s = "0"
 * Output: 0
 * Explanation: There is no character that is mapped to a number starting with '0'. We cannot ignore a zero when we face it while decoding. So, each '0' should be part of "10" --> 'J' or "20" --> 'T'.
 * Example 4:
 *
 * Input: s = "1"
 * Output: 1
 */
public class L91_DecodeWays {
    /**
     * Recursion
     * Time: O(2^n)
     */
    public int numDecodings1(String s) {
        return s.length()==0?0:numDecodings1(0,s);
    }
    private int numDecodings1(int p, String s) {
        int n=s.length();
        if(p==n) return 1;
        if(s.charAt(p)=='0') return 0;
        int res=numDecodings1(p+1,s);
        if(p<n-1&&(s.charAt(p)=='1'||s.charAt(p)=='2'&&s.charAt(p+1)<'7'))
            res+=numDecodings1(p+2,s);
        return res;
    }

    /**
     * Memoization
     */
    public int numDecodings(String s) {
        int n=s.length();
        Integer[] mem = new Integer[n];
        return s.length() == 0 ? 0 : numDecodings(0, s, mem);
    }
    private int numDecodings(int p, String s, Integer[] mem) {
        int n = s.length();
        if(p == n) return 1;
        if(s.charAt(p) == '0') return 0;
        if(mem[p]!= null) return mem[p];
        int res = numDecodings(p+1,s,mem);
        if(p < n-1 && (s.charAt(p) == '1'|| s.charAt(p) == '2' && s.charAt(p+1)<'7'))
            res += numDecodings(p+2, s, mem);
        return mem[p] = res;
    }
    /**
     * DP
     */
    public int numDecodingsDP(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for(int i = n-1; i >= 0; i--)
            if(s.charAt(i) != '0') {
                dp[i] = dp[i + 1];
                if(i < n-1 && (s.charAt(i)=='1'||s.charAt(i)=='2'&&s.charAt(i+1)<'7'))
                    dp[i] += dp[i+2];
            }
        return dp[0];
    }

    /**
     * DP constant space
     */
    public int numDecodings4(String s) {
        int dp1 = 1, dp2 = 0, n = s.length();
        for(int i = n-1; i >= 0; i--) {
            int dp = s.charAt(i) == '0' ? 0 : dp1;
            if(i < n-1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i+1) < '7'))
                dp += dp2;
            dp2 = dp1;
            dp1 = dp;
        }
        return dp1;
    }
}
