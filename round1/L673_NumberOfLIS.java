package round1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L673_NumberOfLIS {
    /**
     * The idea is to use two arrays len[n] and cnt[n] to record the maximum length of Increasing Subsequence and the coresponding number of these sequence which ends with nums[i], respectively. That is:
     *
     * len[i]: the length of the Longest Increasing Subsequence which ends with nums[i].
     * cnt[i]: the number of the Longest Increasing Subsequence which ends with nums[i].
     *
     * Then, the result is the sum of each cnt[i] while its corresponding len[i] is the maximum length.\
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, res = 0, max_len = 0;
        int[] dp =  new int[n], cnt = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = cnt[i] = 1;
            for(int j = 0; j < i; j++){
                if (nums[i] > nums[j]) {
                    if (dp[i] == dp[j] + 1) cnt[i] += cnt[j];
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            if(max_len == dp[i])res += cnt[i];
            if(max_len < dp[i]){
                max_len = dp[i];
                res = cnt[i];
            }
        }
        return res;
    }

    public int findNumberOfLIS2(int[] nums) {
        if (nums.length < 2) return nums.length;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int longest = dp[0];
        List<Integer> counts = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    } else if (dp[j] + 1 == dp[i]) {
                        counts.add(dp[j] + 1);
                    }
                }
            }
            longest = Math.max(longest, dp[i]);
        }
        int count = 0;
        for (Integer c : counts) {
            if (c == longest) count++;
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == longest) count++;
        }
        return count;
    }
    @Test
    public void test(){
        int[] nums = {1, 2, 4, 3, 5, 4, 7};
        System.out.println(findNumberOfLIS(nums));
    }
}
