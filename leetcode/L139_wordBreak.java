package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-03-16
    @time:   16:13


    Follow ups: 1. 有的情况下，all possible sentences
                2. 有的情况下，最少下刀的刀数(DFS深度最小）
                3. 最少刀数下all possible 的sentence
 */
import org.junit.Test;

import java.util.*;

public class L139_wordBreak {
    // Solution 1: Recursion Top-down, Divide and Conquer
    // Time complexity analysis:
    // T(n) = T(n-1) + T(n-2) + T(n-3)...+ 1
    //also, T(n-1) = T(n-2) + T(n-3)...+ 1
    //
    //So:
    //T(n) = T(n-1) + T(n-2) + T(n-3)...+ 1
    //= T(n-1) + T(n-1) // subbing in T(n-1) equation from above
    //= 2 * T(n-1)
    //= 4 * T(n-2)
    //= ...
    //= 2^n // since T(1) = 1
    public boolean wordBreak1(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }


    // Solution 2: Recursion with memoization (pruning), top-down approach
    // T(n): O(n^2) size of recursion tree can go up to n^2
    // Space: O(n) depth of recursion tree
    public boolean wordBreak2(String s, List<String> wordDict) {
        return word_Break2(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break2(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break2(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    // Solution 2v2: recursion with memoization, with hashmap
    public boolean wordBreak2v2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<String>(wordDict);
        Map<String, Boolean> mem = new HashMap<String, Boolean>();
        return wordBreak2v2(s, mem, dict);
    }

    private boolean wordBreak2v2(String s,
                              Map<String, Boolean> mem,
                              Set<String> dict) {

        if (mem.containsKey(s)) return mem.get(s);

        if (dict.contains(s)) {
            mem.put(s, true);
            return true;
        }

        for (int i = 0; i < s.length(); ++i) {
            if (dict.contains(s.substring(i)) && wordBreak2v2(s.substring(0, i), mem, dict)) { // 如果后面的suffix在dict里，递归求解剩下前面的substring，同时
                mem.put(s, true);
                return true;
            }
        }
        mem.put(s, false);
        return false;
    }

    // Solution 3: bottom-up dynamic programming
    // T(n): O(n^2)
    // Definition: dp[i] represents if we can form the string, whose index from [0 to i), with words from dict

    public boolean wordBreak3(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // Solution 3: using arraylist to store every true indices, so we don't need to check
    // T(n): O(k*n)
    // Space: O(k)
    public boolean wordBreak3v2(String s, List<String> wordDict) {
        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < indices.size(); j++) {
                if (wordDict.contains( s.substring(indices.get(j), i))) {
                    indices.add(i);
                    break;
                }
            }
        }
        return indices.get(indices.size() - 1) == s.length();
    }


    // Solution 4: BFS
    // yearly recruiting process. wrap up my current work after 6 weeks .
    //
    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }


    // May 11th 算法加强
    // DFS with pruning
    public boolean wordBreak5(String s, List<String> wordDict) {
        return dfsHelper(s, new HashSet(wordDict), 0, new Boolean[s.length() + 1]);
    }
    public boolean dfsHelper(String s, Set<String> wordDict, int index, Boolean[] mem) {
        if (mem[index] != null) return mem[index];
        if (index == s.length()) return true;

        for (int i = index + 1; i <= s.length(); i++) {
            String subString = s.substring(index, i);
            if (wordDict.contains(subString)) {
                if (dfsHelper(s, wordDict, i, mem)) {
                    mem[index] = true;
                    return true;
                }
            }
        }
        mem[index] = false;
        return false;
    }

    // 算法哥DP
    // dp[i] [i, len) T/F
    public boolean wordBreakDP(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true; // empty string  [len , len)
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subStr = s.substring(i, j);
                if (wordDict.contains(subStr) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    @Test
    public void test() {
      String s = "catsandog";
      List<String> wordDict = new ArrayList<>(Arrays.asList(new String[]{"dog", "cat" ,"cats", "and", "sand"}));

      String t = "leetcode";
      List<String> dict = new ArrayList<>(Arrays.asList(new String[]{"leet", "code"}));
      System.out.println(wordBreak1(t, dict));
    }

}

