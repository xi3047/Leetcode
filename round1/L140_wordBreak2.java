package round1;

import org.junit.Test;

import java.util.*;

public class L140_wordBreak2 {
    // Solution 1: Recursion
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return word_Break(s, wordDict, 0);
    }
    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        return res;
    }


    // Solution 2: Recursion with memoization
    public List<String> wordBreak2(String s, Set<String> wordDict) {
        return word_Break(s, wordDict, 0);
    }
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break2(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break2(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    // Solution 3:  DP
    public List<String> wordBreak3(String s, Set<String> wordDict) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }

    // 算法哥 DFS pruning 时间复杂度指数级，worst Case 一个也捞不到
    public List<String> wordBreakDFS(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        boolean[] mem = new boolean[s.length()];
        dfs(s, new HashSet<String>(wordDict), 0, res, new StringBuilder(), mem);
        return res;
    }

    private void dfs(String s, HashSet<String> wordDict, int index, List<String> res, StringBuilder path, boolean[] mem) {
        if (index == s.length()) {
            res.add(path.toString());
            return;
        }

        if (!mem[index]) return;
        int size = res.size();

        for (int i = index + 1; i <= s.length(); i++) {
            String subStr = s.substring(index, i);
            if (wordDict.contains(subStr)) {
                int len = path.length();
                path.append(subStr);
                dfs(s, wordDict, 0, res, path, mem);
                path.setLength(len);
            }
        }

        // 如果result没发生改变 就说明什么都没找到
        if (res.size() == size) {
            mem[index] = false;
        }
    }

    @Test
    public void test () {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>(Arrays.asList(new String[]{"dog", "cat" ,"cats", "and", "sand"}));
        Set<String> dict = new HashSet<>(wordDict);
        System.out.println(wordBreak(s, dict));
    }
}
