package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-05-31
    @time:   20:29


Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */
public class L410_SplitArrayLargestSum {
    /**
     * Solution1: DFS Pruning
     */
    public int splitArrayPruning(int[] nums, int m) {
        int len = nums.length;
        int[] prefixSum = new int[len];
        prefixSum[0] = nums[0];
        for (int i =1; i < len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        Integer[][] mem = new Integer[len][m + 1];
        return helper(nums, len - 1, m, prefixSum, mem);
    }

    private int helper(int[] nums, int k,  int m, int[] prefixSum, Integer[][] mem) {
        if (m == 1) return prefixSum[k];
        if (mem[k][m] != null) return mem[k][m];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, Math.max(helper(nums, i, m - 1, prefixSum, mem), prefixSum[k] - prefixSum[i]));
        }
        return mem[k][m] = ans;
    }

    /**
     * Solution 2: DP
     * Have Bugs, need to fix
     */

    public int splitArrayDP(int[] nums, int m) {
        int len = nums.length;
        int[][] dp = new int[len + 1][m+ 1];
//        int[] prefixSum = new int[len + 1];
//
//        prefixSum[0] = 0;
//        prefixSum[1] = nums[0];
//        for (int i =2; i <= len; i++) {
//            prefixSum[i] = prefixSum[i ] + nums[i - 1];
//        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;


//        for (int i = 0; i < len; i++) {
//            dp[i][0] = prefixSum[i];
//        }

        for (int i = 0; i <= len; i++) { // 切的位置
            for (int j = 1; j <= m; j++) { // 还需要分几次
                int sum = 0;
                for (int k = i; k >= 0; k--) { // 在所有可以切的位置求值
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum));
                    if (k > 0) sum += nums[i - 1];
                }
            }
        }
        return dp[len][m];
    }

    @Test
    public void test(){
        int[] nums = {7, 2, 5, 10, 8};
        int k = 2;
        System.out.println(splitArrayDP(nums, k));

    }
}
