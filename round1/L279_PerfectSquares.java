package round1;

/*
    @author: Xi Zhang
    @date:   2019-05-03
    @time:   13:09

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */

import org.junit.Test;

import java.util.*;

public class L279_PerfectSquares {

    // BFS
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                for (int i = 0; i * i <= n; i++) {
                    int num = cur + i * i;
                    if (num == n) {
                        return depth + 1;
                    } else if (num > n) {
                        break;
                    }
                    if (!visited.contains(num)) {
                        queue.offer(num);
                        visited.add(num);
                    }
                }
            }
            depth++;
        }
        return depth;
    }

    // Optimized BFS
    public int numSquares2(int n) {
        int root = (int) Math.sqrt(n);
        int count = 0;
        boolean[] visited = new boolean[n];
        int[] squares = new int[root];
        for(int i=1; i<=root; i++) {
            squares[i-1] = i * i;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);

        while(!q.isEmpty()) {
            count++;
            int size = q.size();
            for(int i=0; i<size; i++) {
                int num = q.poll();
                for(int sqr : squares) {
                    int remain = num - sqr;
                    if(remain == 0) {
                        return count;
                    } else if(remain > 0 && !visited[remain]) {
                        visited[remain] = true;
                        q.offer(remain);
                    } else if(remain < 0) {
                        break;
                    }
                }
            }
        }

        return 0;
    }

    // DP
    public int numSquares3(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = n;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }



    @Test
    public void test() {
        System.out.println(numSquares3(13));
    }
}
