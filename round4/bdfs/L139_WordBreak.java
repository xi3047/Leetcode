package round4.bdfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L139_WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length() + 1];
        return dfs(s, 0, wordSet, memo);
    }

    private boolean dfs(String s, int index, Set<String> wordSet, Boolean[] memo) {
        if (memo[index] != null) return memo[index];
        if (index == s.length()) return memo[index] = true;
        for (int end = index + 1; end <= s.length(); end++) {
            String cur = s.substring(index, end);
            if (wordSet.contains(cur)) {
                if (dfs(s, end, wordSet, memo)) {
                    return true;
                }
            }
        }
        return memo[index] = false;
    }
}
