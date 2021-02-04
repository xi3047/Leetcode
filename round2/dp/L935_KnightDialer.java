package round2.dp;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 1/29/21 12:27 上午
 * @topic round2.dp
 * @link https://leetcode.com/problems/knight-dialer/
 * @description
 * Informative Solution by Google Engineer:
 * https://hackernoon.com/google-interview-questions-deconstructed-the-knights-dialer-f780d516f029
 */
public class L935_KnightDialer {
    /**
     * Solution 1:
     * Recursion
     * Time Complexity: 2^n
     */
    public int knightDialer1(int n) {
        int res = 0;
        for (int i = 0; i <= 9; i++) {
            res += countSequence(i, n - 1);
        }
        return res;
    }
    private int countSequence(int num, int n) {
        if (n == 0) {
            return 1;
        }
        int total = 0;
        for (int neighbor : getNeighbors(num)) {
            total += countSequence(neighbor, n - 1);
        }
        return total;
    }

    /**
     * Recursion with memoization
     */
    Map<String, Integer> memo = new HashMap<>();
    public int knightDialer2(int n) {
        int res = 0;
        for (int i = 0; i <= 9; i++) {
            res += countSequenceM(i, n - 1);
        }
        return res;
    }
    private int countSequenceM(int num, int n) {
        String key = num + "-" + n;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (n == 0) {
            return 1;
        }
        int total = 0;
        for (int neighbor : getNeighbors(num)) {
            total += countSequenceM(neighbor, n - 1);
        }
        memo.put(key, total);
        return total;
    }


    /**
     * Dynamic Programming
     */
    public int knightDialer(int N){
        int[][] dirs = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};

        int[][] dp = new int[ N + 1][10];
        for(int j = 0; j < dp[0].length; j ++){
            dp[1][j] = 1;
        }
        int mod = (int)1e9 + 7;
        for(int i = 2; i < dp.length;i ++)
            for(int j = 0; j < dp[0].length; j ++){

                int[] dir = dirs[j]; 	//Where j comes from
                for(int num : dir){
                    dp[i][j] += dp[i - 1][num];
                    dp[i][j] %= mod;
                }
            }
        int count = 0;
        for(int i = 0; i < dp[0].length; i ++){
            count += dp[N][i];
            count %= mod;
        }
        return count;
    }

    public int[] getNeighbors(int n) {
        int[][] dirs = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
        List<Integer> res = new ArrayList<>();
        return dirs[0];
    }
}
