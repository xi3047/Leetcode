package round4.bdfs;

public class L10_RegularExpressionMatching {
    /**
    DFS version
     */
    public boolean isMatch(String s, String p) {
        return dfs(s, p, 0, 0);
    }

    private boolean dfs(String s, String p, int i, int j) {
        if (i == s.length() && j == p.length()) return true;
        if (j == p.length()) return false;

        boolean currentMatch = i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            return dfs(s, p, i, j + 2) ||
                    currentMatch && dfs(s, p, i + 1, j);
        } else {
            return currentMatch && dfs(s, p, i + 1, j + 1);
        }
    }


    /**
     * DFS with pruning
     */
    public boolean isMatch2(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dfsP(s, p, 0, 0, memo);
    }

    private boolean dfsP(String s, String p, int i, int j, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];
        if (i == s.length() && j == p.length()) return true;
        if (j == p.length()) return false;

        boolean currentMatch = i < s.length() && (s.charAt(j) == '.' || s.charAt(i) == p.charAt(j));

        boolean result;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            result = dfsP(s, p, i, j + 2, memo) ||
                    currentMatch && dfsP(s, p, i + 1, j, memo);
        } else {
            result = currentMatch && dfsP(s, p, i + 1, j + 1, memo);
        }
        memo[i][j] = result;
        return result;
    }
}
