package round2.dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 12/14/2020 12:04 AM
 * @topic round2.dp
 * @link
 * @description
 */
public class L1235_MaxProfitJobScheduling {
    /*
    If we sort jobs by start time, starting from job index cur = 0, we might either schedule the jobs[cur] or not.
        -If we schedule jobs[cur], the problem becomes profit of jobs[cur] + max profit of scheduling jobs starting from next available job index.
        -If we don't schedule jobs[cur], the problem becomes max profit of scheduling jobs starting from cur + 1.
    We choose the one giving more profits.

    After observation, there are overlapped subproblems, so we can utilize either memoization or bottom-up DP.

    Note: majority of solutions posted sort jobs by end time, their thinking process should be: starting from job index cur = jobs.length - 1, we might schedule the jobs[cur] or not.

        If we schedule jobs[cur], the problem becomes profit of jobs[cur] + max profit of scheduling jobs ending with nearest previous job index.
        If we don't schedule jobs[cur], the problem becomes max profit of scheduling jobs ending with cur - 1.
        We choose the one giving more profits.
    */

    /*
    Top-down DP (Memoization)
     */
    private Map<Integer, Integer> dp;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        dp = new HashMap<>();
        return dfs(0, jobs);
    }

    private int dfs(int cur, int[][] jobs) {
        if (cur == jobs.length) {
            return 0;
        }

        if (dp.containsKey(cur)) {
            return dp.get(cur);
        }

        int next = findNext(cur, jobs);
        int inclProf = jobs[cur][2] + (next == -1 ? 0 : dfs(next, jobs));
        int exclProf = dfs(cur + 1, jobs);

        dp.put(cur, Math.max(inclProf, exclProf));
        return Math.max(inclProf, exclProf);
    }

    int findNext(int cur, int[][] jobs) {
        for (int next = cur + 1; next < jobs.length; next++) {
            if (jobs[next][0] >= jobs[cur][1]) {
                return next;
            }
        }
        return -1;
    }

    /*
    Bottom-up DP
    We might also build the dp array bottom-up, i.e. cur from end to start in this case.
     */
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        int[] dp = new int[jobs.length];
        dp[jobs.length - 1] = jobs[jobs.length - 1][2];
        for (int cur = jobs.length - 2; cur >= 0; cur--) {
            int next = findNext(cur, jobs);
            dp[cur] = Math.max(
                    jobs[cur][2] + (next == -1 ? 0 : dp[next]),
                    dp[cur + 1]
            );
        }
        return dp[0];
    }


    @Test
    public void test() {
        System.out.println(jobScheduling(new int[]{1,2,3,3}, new int[]{3,4,5,6}, new int[]{50,10,40,70}));
    }
}
