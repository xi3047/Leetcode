package round2.bdfs;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/18/2021 5:04 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/word-break/
 * @description
 */
public class L139_WordBreak {
    /**
     * DFS, Time Limit Exceeded
     */
    static class Solution_1 {
        public boolean wordBreak(String s, List<String> wordDict) {
            return canBreak(s, wordDict, 0);
        }

        private boolean canBreak(String s, List<String> wordDict, int index) {
            if (index == s.length()) {
                return true;
            }
            for (int right = index; right <= s.length(); right++) {
                String curStr = s.substring(index, right);
                if (wordDict.contains(curStr)) {
                    if (canBreak(s, wordDict, right)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    /**
     * DFS with Memoization
     */
    static class Solution_2 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Boolean[] memo = new Boolean[s.length() + 1];
            // memo at 0 -> empty string, at len -> whole string
            return canBreak(s, wordDict, 0, memo);
        }

        private boolean canBreak(String s, List<String> wordDict, int index, Boolean[] memo) {
            if (memo[index] != null) {
                return memo[index];
            }
            if (index == s.length()) {
                return memo[index] = true;
            }
            for (int right = index; right <= s.length(); right++) {
                String curStr = s.substring(index, right);
                if (wordDict.contains(curStr)) {
                    if (canBreak(s, wordDict, right, memo)) {
                        return true;
                    }
                }
            }
            return memo[index] = false;
        }

    }



    @Test
    public void test() {
        System.out.println(new Solution_2().wordBreak("abc", Arrays.asList("ab", "c")));
    }
}
