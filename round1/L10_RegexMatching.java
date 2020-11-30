package round1;
/*
    @author: Xi Zhang
    @date:   2019-03-10
    @time:   21:44
 */
import org.junit.Test;

public class L10_RegexMatching {
    /**
     * DP Solution
     * Regular Expression Matching
     */
    public boolean isMatch(String s, String p) {
        int m=s.length(), n=p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        //初始化第0列，只有X*能匹配空串，如果有*，它的真值一定和p[0][j-2]的相同（略过它之前的符
        for (int j = 1; j <= n; j++) {
            if (j > 1 && p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) { // 当前字符完全匹配
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2]; // 当*为空
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j] | dp[i-1][j]; // 或者是当前字符匹配了*之前的字符(.也代表匹配）
                    }
                }
            }
        }
        return dp[m][n];
    }

    /**
     * DFS with Pruning
     */
    public boolean isMatchDFS(String s, String p) {
        if (s == null || p == null) return false;
        Boolean[][] mem = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(s, 0, p, 0, mem);
    }

    private boolean dfs(String s, int idxS, String p, int idxP, Boolean[][] mem) {
        if (mem[idxS][idxP] != null) return mem[idxS][idxP];

        if (idxP == p.length()) return idxS == s.length();

        if (idxP == p.length() - 1 || p.charAt(idxP + 1) != '*') {
            if (idxS < s.length() && isMatched(s, idxS, p, idxP)) {
                return dfs(s, idxS + 1, p, idxP + 1, mem);
            } else {
                mem[idxS][idxP] = false;
                return false;
            }
        } else { // *
            int i = idxS - 1;
            while (i < s.length() && (i < idxS || isMatched(s, i, p, idxP))) {
                if(dfs(s, i + 1, p, idxP + 2, mem)) {
                    mem[idxS][idxP] = true;
                    return true;
                }
                i++;
            }
            mem[idxS][idxP] = false;
            return false;
        }
    }

    private boolean isMatched(String s, int idxS, String p, int idxP) {
        return p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS);

    }


    // Solution 2: recursion
    public boolean isMatch2(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() > 1 && p.charAt(1) == '*') {  // second char is '*'
            if (isMatch2(s, p.substring(2))) {
                return true;
            }
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
                return isMatch(s.substring(1), p);
            }
            return false;
        } else {                                     // second char is not '*'
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        }
    }
    @Test
    public void test() {
        String s = "xaabyc";
        String p = "xa*b.c";
        System.out.println(isMatchDFS(s, p));
    }

    @Test
    public void test2() {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatchDFS(s, p));
    }
}
