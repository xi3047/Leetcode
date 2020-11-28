package round2.dp;

import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/23/2020 5:57 PM
 * @topic round2.dp
 * @link https://leetcode.com/problems/word-break
 * @description
 */
public class L139_WordBreak {
    /**
     * Recursion with memoization
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length()];
        return helper(s, wordDict, 0, memo);
    }
    private boolean helper(String s, List<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && helper(s, wordDict, end, memo)){
                memo[start] = true;
                return memo[start];
            }
        }
        return memo[start] = false;
    }

    /**
     * Dynamic programming
     */
    public boolean wordBreakDP(String s, List<String> wordDict) {
        boolean [] dp = new boolean[s.length() + 1];
        dp[0]= true;
        for (int end = 1; end <= s.length(); end++) {
            for (int start = 0; start < end; start++) {
                if (dp[start] && wordDict.contains(s.substring(start, end))) {
                    dp[end] = true;
                }
            }
        }
        return dp[s.length()];
    }
}
