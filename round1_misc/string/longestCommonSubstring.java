package round1_misc.string;

import org.junit.Test;

public class longestCommonSubstring {
    public int longestCommonSubstring(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1][len2];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);

                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    @Test
    public void test() {
        String s1 = "lclc";
        String s2 = "clcl";
        System.out.println(longestCommonSubstring(s1, s2));

    }
}
