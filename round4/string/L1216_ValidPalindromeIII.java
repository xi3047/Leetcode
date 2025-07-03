package round4.string;

public class L1216_ValidPalindromeIII {
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        return minDeletions(s, 0, n - 1, memo) <= k;
    }

    private int minDeletions(String s, int i, int j, Integer[][] memo) {
        if (i >= j) return 0;
        if (memo[i][j] != null) return memo[i][j];
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = minDeletions(s, i + 1, j - 1, memo);
        } else {
            int deleteLeft = minDeletions(s, i + 1, j, memo);
            int deleteRight = minDeletions(s, i, j - 1, memo);
            memo[i][j] =  1 + Math.min(deleteLeft, deleteRight);
        }
        return memo[i][j];
    }
}
